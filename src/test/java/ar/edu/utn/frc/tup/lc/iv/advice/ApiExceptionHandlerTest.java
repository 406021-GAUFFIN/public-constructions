package ar.edu.utn.frc.tup.lc.iv.advice;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ApiExceptionHandlerTest {
    /**
     * Tests {@link ApiExceptionHandler#handleAllExceptions(Exception)} for handling generic exceptions.
     *
     * <p>Verifies that the method returns a {@code 500 Internal Server Error} status, the correct error details,
     * and a non-null timestamp.
     *
     * @see ApiExceptionHandler#handleAllExceptions(Exception)
     */
    @Test
    public void testHandleAllExceptions() {
        ApiExceptionHandler handler = new ApiExceptionHandler();
        Exception ex = new Exception("Generic error");

        ResponseEntity<ErrorApi> response = handler.handleAllExceptions(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(500, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals("Internal Server Error", response.getBody().getError());
        assertEquals("Generic error", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());

    }

    /**
     * Tests {@link ApiExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)}
     * for handling validation errors in method arguments.
     *
     * <p>Verifies that the method returns a {@code 400 Bad Request} status, includes the correct error message,
     * a list of validation errors, and a non-null timestamp.
     *
     * @see ApiExceptionHandler#handleMethodArgumentNotValid(MethodArgumentNotValidException)
     */
    @Test
    public void testHandleMethodArgumentNotValid() {

        ApiExceptionHandler handler = new ApiExceptionHandler();


        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(
                new FieldError("object", "field1", "Field1 is required"),
                new FieldError("object", "field2", "Field2 must be greater than 18")
        ));
        MethodParameter parameter = mock(MethodParameter.class);
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter,bindingResult);

        ResponseEntity<ErrorApi> response = handler.handleMethodArgumentNotValid(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals(2, response.getBody().getErrors().size());
        assertEquals("Field1 is required", response.getBody().getErrors().get(0));
        assertEquals("Field2 must be greater than 18", response.getBody().getErrors().get(1));
        assertNotNull(response.getBody().getTimestamp());
    }
}
