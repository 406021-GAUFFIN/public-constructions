package ar.edu.utn.frc.tup.lc.iv.clients;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.RegisterAuthorizationRangesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * Client to interact with the external Accesses service.
 */
@Component
@RequiredArgsConstructor
public class AccessesClient {
    /**
     * WebClient for making HTTP requests.
     */
    private final WebClient webClient;

    /**
     * Base URL for the Contacts service.
     */
    @Value("${accesses.url}")
    private String accessesBaseUrl;

    /**
     * Sends a POST request to Contacts service.
     *
     * @param request contact request DTO
     * @return response DTO
     */
    public AuthorizationRangeResponseDto allowAccess(RegisterAuthorizationRangesDTO request) {


        Mono<AuthorizationRangeResponseDto> response = webClient.post()
                .uri(accessesBaseUrl + "/authorized-ranges/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthorizationRangeResponseDto.class);
        return response.block();
    }
}
