package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.error.PlotServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CadastreClientTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private CadastreClient cadastreClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cadastreClient = new CadastreClient(webClient);
    }

    @Test
    void testPlotExists_ReturnsTrue() {
        Long plotId = 1L;

        // Mock WebClient behavior
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(), any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(true));

        boolean exists = cadastreClient.plotExists(plotId);

        assertTrue(exists);

        // Verify WebClient interactions
        verify(webClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(anyString(), any(), any());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(Boolean.class);
    }

    @Test
    void testPlotExists_ReturnsFalse() {
        Long plotId = 1L;

        // Mock WebClient behavior
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(), any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenReturn(Mono.just(false));

        boolean exists = cadastreClient.plotExists(plotId);

        assertFalse(exists);

        // Verify WebClient interactions
        verify(webClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(anyString(), any(), any());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(Boolean.class);
    }

    @Test
    void testPlotExists_ThrowsPlotServiceException_OnWebClientResponseException() {
        Long plotId = 1L;

        // Mock WebClient throwing WebClientResponseException
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(), any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenThrow(WebClientResponseException.class);

        // Assert that PlotServiceException is thrown
        PlotServiceException exception = assertThrows(PlotServiceException.class, () -> cadastreClient.plotExists(plotId));

        assertTrue(exception.getMessage().contains("Error connecting to Plot service"));

        // Verify WebClient interactions
        verify(webClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(anyString(), any(), any());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(Boolean.class);
    }

    @Test
    void testPlotExists_ThrowsPlotServiceException_OnGeneralException() {
        Long plotId = 1L;

        // Mock WebClient throwing a generic exception
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), any(), any())).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(Boolean.class)).thenThrow(RuntimeException.class);

        // Assert that PlotServiceException is thrown
        PlotServiceException exception = assertThrows(PlotServiceException.class, () -> cadastreClient.plotExists(plotId));

        assertTrue(exception.getMessage().contains("Unexpected error"));

        // Verify WebClient interactions
        verify(webClient, times(1)).get();
        verify(requestHeadersUriSpec, times(1)).uri(anyString(), any(), any());
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(Boolean.class);
    }
}
