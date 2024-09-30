package ar.edu.utn.frc.tup.lc.iv.services.implementation;


import ar.edu.utn.frc.tup.lc.iv.clients.ContactsClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081"
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

    @Mock
    private ConstructionRepository constructionRepository;


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




}
