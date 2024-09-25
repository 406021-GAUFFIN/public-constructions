package ar.edu.utn.frc.tup.lc.iv.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class to set up WebClient beans.
 * This configuration provides a WebClient instance and its Builder
 * to be used throughout the application for making HTTP requests.
 */
@Configuration
public class WebClientConfig {

    /**
     * WebClient bean to make HTTP requests.
     * @param builder used to build and configure
     *                the WebClient instance.
     * @return the WebClient instance.
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

    /**
     * WebClient.Builder bean.
     * @return the Webclient builder instance.
     */
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
