package ar.edu.utn.frc.tup.lc.iv.clients;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.ContactRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.ContactResponseDto;
import ar.edu.utn.frc.tup.lc.iv.error.ContactServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ContactsClient {

    private final WebClient webClient;

    @Value("${contacts.url}")
    private String contactsBaseUrl;


    public ContactResponseDto getContact(ContactRequestDto request) {
        request.setContactType("PHONE");

            try {
                Mono<ContactResponseDto> response = webClient.post()
                        .uri( contactsBaseUrl+"/contacts")
                        .bodyValue(request)
                        .retrieve()
                        .bodyToMono(ContactResponseDto.class);
                return response.block();
            } catch (WebClientResponseException ex) {
                throw new ContactServiceException("Error connecting to Worker service: " + ex.getMessage());
            } catch (Exception ex) {
                throw new ContactServiceException("Unexpected error: " + ex.getMessage());
            }
    }
}
