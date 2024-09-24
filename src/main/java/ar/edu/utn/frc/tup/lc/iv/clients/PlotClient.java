package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.error.PlotServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 * Client for interacting with the Plot microservice.
 */
@Component
@RequiredArgsConstructor
public class PlotClient {

    /**
     * WebClient instance to make HTTP requests.
     */
    private final WebClient webClient;

    /**
     * Base URL for the Plot microservice.
     */
    String BASE_URL = "http://cadastre-service/api";

    /**
     * Verifies if a plot exists by its ID.
     *
     * @param plotId The ID of the plot.
     * @return true if the plot exists, false otherwise.
     */
    public boolean plotExists(Long plotId) {
        try {
            Mono<Boolean> response = webClient.get()
                    .uri("{baseUrl}/plots/{plotId}/exists", BASE_URL, plotId)
                    .retrieve()
                    .bodyToMono(Boolean.class);

            return Boolean.TRUE.equals(response.block());
        } catch (WebClientResponseException ex) {
            throw new PlotServiceException("Error connecting to Plot service: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new PlotServiceException("Unexpected error: " + ex.getMessage(), ex);
        }
    }
}
