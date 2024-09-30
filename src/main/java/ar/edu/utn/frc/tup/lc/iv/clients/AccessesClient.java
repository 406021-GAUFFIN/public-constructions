package ar.edu.utn.frc.tup.lc.iv.clients;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponse;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.RegisterAuthorizationRangesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
    public AuthorizationRangeResponse allowAccess(RegisterAuthorizationRangesDTO request) {


        Mono<AuthorizationRangeResponse> response = webClient.post()
                .uri(accessesBaseUrl + "/authorized-ranges/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthorizationRangeResponse.class);
        return response.block();
    }
}
