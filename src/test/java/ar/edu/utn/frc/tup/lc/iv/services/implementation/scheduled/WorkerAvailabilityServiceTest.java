package ar.edu.utn.frc.tup.lc.iv.services.implementation.scheduled;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;

import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;

import ar.edu.utn.frc.tup.lc.iv.services.implementation.DocumentationTypeServiceImpl;
import ar.edu.utn.frc.tup.lc.iv.services.scheduled.WorkerAvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
public class WorkerAvailabilityServiceTest {

    @Mock
    private WorkerRepository workerRepository;

    @InjectMocks
    private WorkerAvailabilityService workerAvailabilityService;


    @Test
    public void testWorkerDocumentationExpired() {
        // Setup a worker with expired documentation
        WorkerEntity worker = new WorkerEntity();

        WorkerDocumentationEntity documentation = new WorkerDocumentationEntity();
        documentation.setExpireDate(LocalDate.now().minusDays(1));
        worker.setDocumentationWorker(new ArrayList<>());
        worker.getDocumentationWorker().add(documentation);
        worker.setAvailableToWork(true); // Initially set to available

        when(workerRepository.findAll()).thenReturn(List.of(worker));

        // Act
        workerAvailabilityService.checkWorkerDocumentation();

        // Assert
        assertFalse(worker.getAvailableToWork()); // Should be set to false after the check
        verify(workerRepository, times(1)).save(worker); // Ensure the save method is called
    }

    @Test
    public void testWorkerDocumentationValid() {
        // Setup a worker with valid documentation
        WorkerEntity worker = new WorkerEntity();
        worker.setDocumentationWorker(List.of(createWorkerDocumentation(LocalDate.now().plusDays(1))));
        worker.setAvailableToWork(false); // Initially set to unavailable

        when(workerRepository.findAll()).thenReturn(List.of(worker));

        // Act
        workerAvailabilityService.checkWorkerDocumentation();

        // Assert
        assertTrue(worker.getAvailableToWork()); // Should be set to true after the check
        verify(workerRepository, times(1)).save(worker); // Ensure the save method is called
    }

    @Test
    public void testWorkerNoDocumentation() {
        // Setup a worker with no documentation
        WorkerEntity worker = new WorkerEntity();
        worker.setDocumentationWorker(Collections.emptyList());
        worker.setAvailableToWork(true);

        when(workerRepository.findAll()).thenReturn(List.of(worker));

        // Act
        workerAvailabilityService.checkWorkerDocumentation();

        // Assert
        assertFalse(worker.getAvailableToWork()); // No documentation, should remain the same
        verify(workerRepository, never()).save(worker); // No save should be called
    }

    // Helper method to create a WorkerDocumentationEntity with a specific expiration date
    private WorkerDocumentationEntity createWorkerDocumentation(LocalDate expireDate) {
        WorkerDocumentationEntity documentation = new WorkerDocumentationEntity();
        documentation.setExpireDate(expireDate);
        return documentation;
    }
}
