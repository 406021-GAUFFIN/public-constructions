package ar.edu.utn.frc.tup.lc.iv.services.scheduled;


import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerAvailabilityService {


    @Autowired
    private WorkerRepository workerRepository;


    @Scheduled(cron = "0 20 18 * * ?") // 24 hours = 86400000 ms
    public void checkWorkerDocumentation() {

        List<WorkerEntity> workers = workerRepository.findAll();

        LocalDate currentDate = LocalDate.now();

        for (WorkerEntity worker : workers) {

            Optional<WorkerDocumentationEntity> latestDocumentation = worker.getDocumentationWorker().stream()
                    .max(Comparator.comparing(WorkerDocumentationEntity::getExpireDate));


            if (latestDocumentation.isPresent()) {
                if (latestDocumentation.get().getExpireDate().isBefore(currentDate)) {
                    worker.setAvailableToWork(false);
                    workerRepository.save(worker);
                    //here inform that worker is no longer available to work to access
                } else {
                    worker.setAvailableToWork(true);
                    workerRepository.save(worker);
                }
            }
        }
    }
}
