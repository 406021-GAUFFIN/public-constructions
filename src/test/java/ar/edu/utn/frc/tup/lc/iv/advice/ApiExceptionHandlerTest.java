package ar.edu.utn.frc.tup.lc.iv.advice;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.error.UpdateConstructionStatusException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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


    @InjectMocks
    private ApiExceptionHandler apiExceptionHandler;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, Objects.requireNonNull(response.getBody()).getStatus());
        assertEquals("Bad Request", response.getBody().getError());
        assertEquals("Generic error", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());

    }

    @Test
    public void testHandleMethodArgumentNotValid() {

        FieldError fieldError = new FieldError("object", "field", "Field is invalid");
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));

        MethodParameter parameter = mock(MethodParameter.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleMethodArgumentNotValid(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals("Field is invalid", response.getBody().getError());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleMethodArgumentNotValid_NoErrors() {

        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList());

        MethodParameter parameter = mock(MethodParameter.class);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, bindingResult);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleMethodArgumentNotValid(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertEquals("Validation failed", response.getBody().getMessage());
        assertEquals("Validation error occurred", response.getBody().getError());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleConstructionNotFoundException() {

        String errorMessage = "Construction with ID 1 not found.";
        ConstructionNotFoundException exception = new ConstructionNotFoundException(errorMessage);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleConstructionNotFoundException(exception);


        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), response.getBody().getError());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleUpdateConstructionStatusException() {

        String errorMessage = "Cannot update construction status.";
        UpdateConstructionStatusException exception = new UpdateConstructionStatusException(errorMessage);


        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleUpdateConstructionStatusException(exception);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getBody().getError());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

}

