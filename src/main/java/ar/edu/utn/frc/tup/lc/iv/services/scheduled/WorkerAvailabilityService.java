package ar.edu.utn.frc.tup.lc.iv.services.scheduled;

import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service to check and update the availability of
 * workers based on their documentation expiration.
 */
@Service
@RequiredArgsConstructor
public class WorkerAvailabilityService {

    /**
     * Repository for accessing worker data.
     */
    private final WorkerRepository workerSource;


    /**
     * Scheduled task to check worker documentation expiration daily.
     * Updates worker availability based on the latest documentation.
     */
    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void checkWorkerDocumentation() {

        List<WorkerEntity> workers = workerSource.findAll();
        LocalDate currentDate = LocalDate.now();

        for (WorkerEntity worker : workers) {
            Optional<WorkerDocumentationEntity> latestDocumentation = worker.getDocumentationWorker().stream()
                    .max(Comparator.comparing(WorkerDocumentationEntity::getExpireDate));

            if (latestDocumentation.isPresent()) {
                if (latestDocumentation.get().getExpireDate().isBefore(currentDate)) {
                    worker.setAvailableToWork(false);
                    workerSource.save(worker);
                    // Inform that the worker is no longer available to work
                } else {
                    worker.setAvailableToWork(true);
                    workerSource.save(worker);
                }
            }
        }
    }
}
