package ar.edu.utn.frc.tup.lc.iv.services.implementation;


import ar.edu.utn.frc.tup.lc.iv.clients.AccessesClient;
import ar.edu.utn.frc.tup.lc.iv.clients.ContactsClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.RegisterAuthorizationRangesDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.error.WorkerNotAvailableException;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerDocumentationService;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
public class WorkerServiceImplTest {

    @MockBean
    private WorkerRepository workerRepository;

    @MockBean
    private ContactsClient contactsClient;

    @Autowired
    private WorkerService workerService;
    @Qualifier("modelMapper")
    @Autowired

    private ModelMapper modelMapper;

    @MockBean
    private WorkerDocumentationService workerDocumentationService;

    @Mock
    private ConstructionRepository constructionRepository;

    @Mock
    private AccessesClient accessesClient;


    @Test
    public void testCreateWorkerWithDocument() {
        WorkerRequestDto workerRequestDto = new WorkerRequestDto();
        workerRequestDto.setConstructionId(1L);
        workerRequestDto.setContact(null);
        workerRequestDto.setDocument("45082541");

        ConstructionEntity constructionEntity = new ConstructionEntity();
        constructionEntity.setId(1L);

        ContactResponseDto contactResponseDto = new ContactResponseDto();
        contactResponseDto.setId(1);

        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setId(1L);
        workerEntity.setContactId(contactResponseDto.getId());
        workerEntity.setDocument("45082541");

        WorkerResponseDto expectedResponse = new WorkerResponseDto();
        expectedResponse.setContact(null);
        expectedResponse.setCuil(null);
        expectedResponse.setDocument("45082541");

        when(constructionRepository.findById(1L)).thenReturn(Optional.of(constructionEntity));
        when(contactsClient.getContact(any())).thenReturn(contactResponseDto);
        when(workerRepository.save(any(WorkerEntity.class))).thenReturn(workerEntity);

        WorkerResponseDto actualResponse = workerService.createWorker(workerRequestDto);


        assertEquals(workerEntity.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getCuil(), actualResponse.getCuil());
        assertEquals(expectedResponse.getDocument(), actualResponse.getDocument());


    }

    @Test
    public void testCreateWorkerWithCuil() {
        WorkerRequestDto workerRequestDto = new WorkerRequestDto();
        workerRequestDto.setConstructionId(1L);
        workerRequestDto.setContact(null);
        workerRequestDto.setCuil("20450825418");

        ConstructionEntity constructionEntity = new ConstructionEntity();
        constructionEntity.setId(1L);

        ContactResponseDto contactResponseDto = new ContactResponseDto();
        contactResponseDto.setId(1);

        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setId(1L);
        workerEntity.setContactId(contactResponseDto.getId());
        workerEntity.setCuil("20450825418");

        WorkerResponseDto expectedResponse = new WorkerResponseDto();
        expectedResponse.setContact(null);
        expectedResponse.setCuil(null);
        expectedResponse.setCuil("20450825418");

        when(constructionRepository.findById(1L)).thenReturn(Optional.of(constructionEntity));
        when(contactsClient.getContact(any())).thenReturn(contactResponseDto);
        when(workerRepository.save(any(WorkerEntity.class))).thenReturn(workerEntity);

        WorkerResponseDto actualResponse = workerService.createWorker(workerRequestDto);


        assertEquals(workerEntity.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getCuil(), actualResponse.getCuil());
        assertEquals(expectedResponse.getDocument(), actualResponse.getDocument());


    }

    @Test
    public void testCreateWorker_ConstructionNotFound() {
        WorkerRequestDto workerRequestDto = new WorkerRequestDto();
        workerRequestDto.setConstructionId(2L);
        workerRequestDto.setDocument("45082541");

        when(constructionRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ConstructionNotFoundException.class, () -> {
            workerService.createWorker(workerRequestDto);
        });

    }


    @Test
    public void testAddWorkerDocumentation() {

        WorkerDocumentationRequestDto requestDto = new WorkerDocumentationRequestDto();
        requestDto.setWorkerId(1L);
        requestDto.setExpireDate("30/12/2025");
        requestDto.setCreatedBy(1);


        WorkerEntity worker = new WorkerEntity();
        worker.setId(1L);
        worker.setName("John");
        worker.setLastName("Doe");
        worker.setAvailableToWork(false);
        worker.setDocumentationWorker(new ArrayList<>());


        WorkerDocumentationEntity documentationEntity = new WorkerDocumentationEntity();
        documentationEntity.setExpireDate(LocalDate.parse(requestDto.getExpireDate(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        documentationEntity.setWorker(worker);


        WorkerDocumentationResponseDto expectedResponse = new WorkerDocumentationResponseDto();
        expectedResponse.setWorkerId(worker.getId());
        expectedResponse.setWorkerFullName(worker.getName() + " " + worker.getLastName());
        expectedResponse.setExpireDate(requestDto.getExpireDate());

        when(workerRepository.findById(requestDto.getWorkerId())).thenReturn(Optional.of(worker));
        when(workerDocumentationService.createWorkerDocumentation(any(WorkerDocumentationRequestDto.class))).thenReturn(documentationEntity);
        when(workerRepository.save(any(WorkerEntity.class))).thenReturn(worker);


        WorkerDocumentationResponseDto actualResponse = workerService.addWorkerDocumentation(requestDto);


        assertEquals(expectedResponse.getWorkerId(), actualResponse.getWorkerId());
        assertEquals(expectedResponse.getExpireDate(), actualResponse.getExpireDate());
        assertEquals(expectedResponse.getWorkerFullName(), actualResponse.getWorkerFullName());
        assertEquals(true, worker.getAvailableToWork());
    }


    @Test
    public void testAllowWorkerAccess_Success() {
        // Given
        Long workerId = 1L;
        String comment = "Worker access granted";

        WorkerEntity worker = new WorkerEntity();
        worker.setId(workerId);
        worker.setAvailableToWork(true);
        worker.setDocument("12345678");
        worker.setCuil("20345678901");

        ConstructionEntity constructionEntity = new ConstructionEntity();
        constructionEntity.setDescription("construction");

        worker.setConstruction(constructionEntity);
        worker.setDocumentationWorker(new ArrayList<>());

        WorkerDocumentationEntity documentation = new WorkerDocumentationEntity();
        documentation.setExpireDate(LocalDate.of(2025, 12, 31));

        when(workerRepository.findById(workerId)).thenReturn(Optional.of(worker));
        when(workerDocumentationService.getLatestWorkerDocumentation(workerId)).thenReturn(documentation);

        AuthorizationRangeResponseDto expectedResponse = new AuthorizationRangeResponseDto();
        when(accessesClient.allowAccess(any(RegisterAuthorizationRangesDTO.class))).thenReturn(expectedResponse);
        expectedResponse.setComment(comment);

        // When
        AuthorizationRangeResponseDto actualResponse = workerService.allowWorkerAccess(workerId, comment);

        // Then
        assertEquals(expectedResponse.getComment(), actualResponse.getComment());

    }

    @Test
    public void testAllowWorkerAccess_WorkerNotFound() {
        // Given
        Long workerId = 1L;
        String comment = "Worker access request";

        when(workerRepository.findById(workerId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(EntityNotFoundException.class, () -> workerService.allowWorkerAccess(workerId, comment));
        verify(accessesClient, never()).allowAccess(any());
    }

    @Test
    public void testAllowWorkerAccess_WorkerNotAvailable() {
        // Given
        Long workerId = 1L;
        String comment = "Worker access request";

        WorkerEntity worker = new WorkerEntity();
        worker.setId(workerId);
        worker.setAvailableToWork(false);  // Worker is not available to work

        when(workerRepository.findById(workerId)).thenReturn(Optional.of(worker));

        // When & Then
        assertThrows(WorkerNotAvailableException.class, () -> workerService.allowWorkerAccess(workerId, comment));
        verify(accessesClient, never()).allowAccess(any());

    }
}
