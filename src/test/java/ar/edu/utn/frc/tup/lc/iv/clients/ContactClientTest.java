package ar.edu.utn.frc.tup.lc.iv.clients;

import ar.edu.utn.frc.tup.lc.iv.clients.ContactsClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class ContactClientTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @InjectMocks
    private ContactsClient contactsClient;

    private ContactRequestDto contactRequestDto;
    private ContactResponseDto contactResponseDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        contactRequestDto = new ContactRequestDto();
        contactRequestDto.setContactValue("123456789");

        contactResponseDto = new ContactResponseDto();
        contactResponseDto.setId(1);
        contactResponseDto.setContactValue("123456789");
        contactResponseDto.setContactType("PHONE");

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(any(ContactRequestDto.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(ContactResponseDto.class)).thenReturn(Mono.just(contactResponseDto));
    }

    @Test
    void testGetContact() {
        ContactResponseDto response = contactsClient.getContact(contactRequestDto);

        // Verify the request type was set to PHONE
        assertEquals("PHONE", contactRequestDto.getContactType());

        // Verify the returned response is as expected
        assertEquals(contactResponseDto.getId(), response.getId());
        assertEquals(contactResponseDto.getContactValue(), response.getContactValue());
        assertEquals(contactResponseDto.getContactType(), response.getContactType());

        // Verify interactions with WebClient
        verify(webClient, times(1)).post();
        verify(requestBodyUriSpec, times(1)).uri(any(String.class));
        verify(requestBodyUriSpec, times(1)).bodyValue(any(ContactRequestDto.class));
        verify(requestHeadersSpec, times(1)).retrieve();
        verify(responseSpec, times(1)).bodyToMono(ContactResponseDto.class);
    }
}

