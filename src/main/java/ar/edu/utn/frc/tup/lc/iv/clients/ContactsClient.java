package ar.edu.utn.frc.tup.lc.iv.clients;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


/**
 * Client to interact with the external Contacts service.
 */

@Component
@RequiredArgsConstructor
public class ContactsClient {

    /**
     * WebClient for making HTTP requests.
     */
    private final WebClient webClient;

    /**
     * Base URL for the Contacts service.
     */
    @Value("${contacts.url}")
    private String contactsBaseUrl;

    /**
     * Sends a POST request to Contacts service.
     *
     * @param request contact request DTO
     * @return response DTO
     */
    public ContactResponseDto getContact(ContactRequestDto request) {
        request.setContactType("PHONE");

        Mono<ContactResponseDto> response = webClient.post()
                .uri(contactsBaseUrl + "/contacts")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ContactResponseDto.class);
        return response.block();
    }
}
