package ar.edu.utn.frc.tup.lc.iv.advice;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Api exception handler.
 */
@RestControllerAdvice
@Getter
@NoArgsConstructor
public class ApiExceptionHandler {
    /**
     * Handles unhandled exceptions and returns a structured error response.
     *
     * @param ex the exception that provides the error message for the response.
     * @return a {@link ResponseEntity} with an {@link ErrorApi} object,
     * status {@code 500 Internal Server Error},
     * and includes a timestamp, status, error name, and message.
     */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleAllExceptions(Exception ex) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ErrorApi error = ErrorApi.builder()
                .timestamp(timeStamp)
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Handles validation errors for method arguments.
     *
     * @param ex the exception with validation errors.
     * @return a {@link ResponseEntity} with an {@link ErrorApi} object,
     *         status {@code 400 Bad Request}, and validation error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());


        ErrorApi error = ErrorApi.builder()
                .timestamp(timestamp)
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message("Validation failed")
                .errors(errors)
                .build();

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
