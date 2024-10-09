package ar.edu.utn.frc.tup.lc.iv.services.implementation;


import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerDocumentationRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerDocumentationService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
public class WorkerDocumentationImplTest {

    @MockBean
    private WorkerRepository workerRepository;

    @MockBean
    private WorkerDocumentationRepository workerDocumentationRepository;

    @Autowired
    private WorkerDocumentationService workerDocumentationService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void testCreateWorkerDocumentation_Success() {
        // Given
        WorkerDocumentationRequestDto requestDto = new WorkerDocumentationRequestDto();
        requestDto.setWorkerId(1L);
        requestDto.setExpireDate("31/12/2025");
        requestDto.setCreatedBy(1);

        WorkerEntity worker = new WorkerEntity();
        worker.setId(1L);

        WorkerDocumentationEntity expectedDocumentationEntity = new WorkerDocumentationEntity();
        expectedDocumentationEntity.setWorker(worker);
        expectedDocumentationEntity.setCreatedBy(1);
        expectedDocumentationEntity.setExpireDate(LocalDate.of(2025, 12, 31));

        when(workerRepository.findById(requestDto.getWorkerId())).thenReturn(Optional.of(worker));
        when(workerDocumentationRepository.save(any(WorkerDocumentationEntity.class)))
                .thenReturn(expectedDocumentationEntity);

        // When
        WorkerDocumentationEntity actualDocumentationEntity = workerDocumentationService.createWorkerDocumentation(requestDto);

        // Then
        assertNotNull(actualDocumentationEntity);
        assertEquals(worker, actualDocumentationEntity.getWorker());
        assertEquals(1, actualDocumentationEntity.getCreatedBy());
        assertEquals(LocalDate.of(2025, 12, 31), actualDocumentationEntity.getExpireDate());

        verify(workerRepository, times(1)).findById(requestDto.getWorkerId());
        verify(workerDocumentationRepository, times(1)).save(any(WorkerDocumentationEntity.class));
    }

    @Test
    public void testCreateWorkerDocumentation_WorkerNotFound() {
        // Given
        WorkerDocumentationRequestDto requestDto = new WorkerDocumentationRequestDto();
        requestDto.setWorkerId(999L);
        requestDto.setExpireDate("31/12/2025");

        when(workerRepository.findById(requestDto.getWorkerId())).thenReturn(Optional.empty());

        // When and Then
        assertThrows(EntityNotFoundException.class, () -> {
            workerDocumentationService.createWorkerDocumentation(requestDto);
        });

        verify(workerRepository, times(1)).findById(requestDto.getWorkerId());
        verify(workerDocumentationRepository, times(0)).save(any(WorkerDocumentationEntity.class));
    }

    @Test
    public void testGetLatestWorkerDocumentation_Success() {
        // Given
        Long workerId = 1L;

        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setId(workerId);
        workerEntity.setDocumentationWorker(new ArrayList<>());

        WorkerDocumentationEntity documentation1 = new WorkerDocumentationEntity();
        documentation1.setExpireDate(LocalDate.of(2023, 12, 31));

        WorkerDocumentationEntity documentation2 = new WorkerDocumentationEntity();
        documentation2.setExpireDate(LocalDate.of(2025, 12, 31));

        workerEntity.getDocumentationWorker().add(documentation1);
        workerEntity.getDocumentationWorker().add(documentation2);

        when(workerRepository.findById(workerId)).thenReturn(Optional.of(workerEntity));

        // When
        WorkerDocumentationEntity actualDocumentation = workerDocumentationService.getLatestWorkerDocumentation(workerId);

        // Then
        assertNotNull(actualDocumentation);
        assertEquals(LocalDate.of(2025, 12, 31), actualDocumentation.getExpireDate());

        verify(workerRepository, times(1)).findById(workerId);
    }

    @Test
    public void testGetLatestWorkerDocumentation_WorkerNotFound() {
        // Given
        Long workerId = 999L;

        when(workerRepository.findById(workerId)).thenReturn(Optional.empty());

        // When and Then
        assertThrows(EntityNotFoundException.class, () -> {
            workerDocumentationService.getLatestWorkerDocumentation(workerId);
        });

        verify(workerRepository, times(1)).findById(workerId);
    }

    @Test
    public void testGetLatestWorkerDocumentation_NoDocumentationFound() {
        // Given
        Long workerId = 1L;

        WorkerEntity workerEntity = new WorkerEntity();
        workerEntity.setId(workerId);
        workerEntity.setDocumentationWorker(new ArrayList<>());  // No documentation

        when(workerRepository.findById(workerId)).thenReturn(Optional.of(workerEntity));

        // When and Then
        assertThrows(EntityNotFoundException.class, () -> {
            workerDocumentationService.getLatestWorkerDocumentation(workerId);
        });

        verify(workerRepository, times(1)).findById(workerId);
    }

    

}
