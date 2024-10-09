package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.RegisterAuthorizationRangesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccessClientTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private AccessesClient accessesClient;

    private RegisterAuthorizationRangesDTO registerAuthorizationRangesDTO;
    private AuthorizationRangeResponseDto authorizationRangeResponseDto;

    @Value("${accesses.url}")
    private String accessesBaseUrl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize the DTOs for the test
        registerAuthorizationRangesDTO = new RegisterAuthorizationRangesDTO();
        registerAuthorizationRangesDTO.setAuthTypeId(1L);
        registerAuthorizationRangesDTO.setVisitorId(1L);
        registerAuthorizationRangesDTO.setExternalId(1L);
        registerAuthorizationRangesDTO.setDateFrom(LocalDate.of(2023, 9, 30));
        registerAuthorizationRangesDTO.setDateTo(LocalDate.of(2023, 10, 30));
        registerAuthorizationRangesDTO.setHourFrom(LocalTime.of(9, 0));
        registerAuthorizationRangesDTO.setHourTo(LocalTime.of(17, 0));
        registerAuthorizationRangesDTO.setPlotId(1L);
        registerAuthorizationRangesDTO.setComment("Test Comment");

        authorizationRangeResponseDto = new AuthorizationRangeResponseDto();
        authorizationRangeResponseDto.setAuthRangeId(1L);
        authorizationRangeResponseDto.setAuthType(1L);
        authorizationRangeResponseDto.setVisitorId(1L);
        authorizationRangeResponseDto.setExternalId(1L);
        authorizationRangeResponseDto.setDateFrom(LocalDate.of(2023, 9, 30));
        authorizationRangeResponseDto.setDateTo(LocalDate.of(2023, 10, 30));
        authorizationRangeResponseDto.setHourFrom(LocalTime.of(9, 0));
        authorizationRangeResponseDto.setHourTo(LocalTime.of(17, 0));
        authorizationRangeResponseDto.setPlotId(1L);
        authorizationRangeResponseDto.setComment("Test Comment");
        authorizationRangeResponseDto.setActive(true); // Set any additional fields as needed

        // Mocking the WebClient interactions
        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(any(RegisterAuthorizationRangesDTO.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(AuthorizationRangeResponseDto.class)).thenReturn(Mono.just(authorizationRangeResponseDto));
    }

    @Test
    void testAllowAccess() {
        // Act
        AuthorizationRangeResponseDto response = accessesClient.allowAccess(registerAuthorizationRangesDTO);

        // Assert: Verify the response is as expected
        assertEquals(authorizationRangeResponseDto.getAuthRangeId(), response.getAuthRangeId());
        assertEquals(authorizationRangeResponseDto.getAuthType(), response.getAuthType());
        assertEquals(authorizationRangeResponseDto.getVisitorId(), response.getVisitorId());
        assertEquals(authorizationRangeResponseDto.getExternalId(), response.getExternalId());
        assertEquals(authorizationRangeResponseDto.getDateFrom(), response.getDateFrom());
        assertEquals(authorizationRangeResponseDto.getDateTo(), response.getDateTo());
        assertEquals(authorizationRangeResponseDto.getHourFrom(), response.getHourFrom());
        assertEquals(authorizationRangeResponseDto.getHourTo(), response.getHourTo());
        assertEquals(authorizationRangeResponseDto.getPlotId(), response.getPlotId());
        assertEquals(authorizationRangeResponseDto.getComment(), response.getComment());
        assertEquals(authorizationRangeResponseDto.isActive(), response.isActive());

        // Verify interactions with WebClient
        verify(webClient, times(1)).post();
        verify(requestBodyUriSpec, times(1)).uri(accessesBaseUrl + "/authorized-ranges/register");
        verify(requestBodyUriSpec, times(1)).bodyValue(registerAuthorizationRangesDTO);
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(AuthorizationRangeResponseDto.class);
    }
}
