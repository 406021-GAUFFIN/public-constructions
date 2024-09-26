package ar.edu.utn.frc.tup.lc.iv.advice;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.error.*;
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

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081"
})
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

    @Test
    public void testHandlePlotNotFoundException() {
        PlotNotFoundException ex = new PlotNotFoundException("Plot not found");

        ResponseEntity<ErrorApi> response = apiExceptionHandler.handlePlotNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), response.getBody().getError());
        assertEquals("Plot not found", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleConstructionAlreadyExistsException() {
        ConstructionAlreadyExistsException ex = new ConstructionAlreadyExistsException("Construction already exists");

        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleConstructionAlreadyExistsException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CONFLICT.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), response.getBody().getError());
        assertEquals("Construction already exists", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandlePlotServiceException() {
        PlotServiceException ex = new PlotServiceException("Service unavailable");

        ResponseEntity<ErrorApi> response = apiExceptionHandler.handlePlotServiceException(ex);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(), response.getBody().getError());
        assertEquals("Service unavailable", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleContactServiceException() {
        ContactServiceException ex = new ContactServiceException("Contact service unavailable");

        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleContactServiceException(ex);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase(), response.getBody().getError());
        assertEquals("Contact service unavailable", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleWorkerAlreadyExistsException() {
        WorkerAlreadyExistsException ex = new WorkerAlreadyExistsException("Worker already exists");

        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleWorkerAlreadyExistsException(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CONFLICT.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.CONFLICT.getReasonPhrase(), response.getBody().getError());
        assertEquals("Worker already exists", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

    @Test
    public void testHandleWorkerCreationException() {
        WorkerCreationException ex = new WorkerCreationException("Worker creation failed");

        ResponseEntity<ErrorApi> response = apiExceptionHandler.handleWorkerCreationException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getStatus());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), response.getBody().getError());
        assertEquals("Worker creation failed", response.getBody().getMessage());
        assertNotNull(response.getBody().getTimestamp());
    }

}

