package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerDocumentationRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerDocumentationService;
import jakarta.persistence.EntityNotFoundException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkerDocumentationServiceImpl implements WorkerDocumentationService {

    /**
     * Repository for accessing construction entities.
     */
    private final WorkerDocumentationRepository workerDocumentationRepository;

    private final WorkerRepository workerRepository;

    /**
     * Model mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public WorkerDocumentationEntity createWorkerDocumentation(WorkerDocumentationRequestDto requestDto) {
        Optional<WorkerEntity> workerEntity = workerRepository.findById(requestDto.getWorkerId());
        if (workerEntity.isEmpty()) {
            throw new EntityNotFoundException("Worker with id " + requestDto.getWorkerId() + " not found");
        }
        WorkerEntity worker = workerEntity.get();
        validateDocumentationExpireDate(requestDto);
        WorkerDocumentationEntity documentationEntity = modelMapper.map(requestDto, WorkerDocumentationEntity.class);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expireDate;
        expireDate = LocalDate.parse(requestDto.getExpireDate(), formatter);
        documentationEntity.setWorker(worker);
        documentationEntity.setCreatedBy(requestDto.getCreatedBy());
        documentationEntity.setExpireDate(expireDate);
        return workerDocumentationRepository.save(documentationEntity);


    }

    @Override
    public WorkerDocumentationEntity getLatestWorkerDocumentation(Long workerId) {

        Optional<WorkerEntity> workerEntity = workerRepository.findById(workerId);
        if (workerEntity.isEmpty()) {
            throw new EntityNotFoundException("Worker with id " + workerId + " not found");
        }
        Optional<WorkerDocumentationEntity> latestDocumentation = workerEntity.get().getDocumentationWorker().stream()
                .max(Comparator.comparing(WorkerDocumentationEntity::getExpireDate));
        if(latestDocumentation.isEmpty()) {
          throw new EntityNotFoundException("Documentation not found for that worker");
        }
        return latestDocumentation.get();
    }


    private void validateDocumentationExpireDate(WorkerDocumentationRequestDto requestDto) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");


        Date expireDate;
        try {
            expireDate = dateFormat.parse(requestDto.getExpireDate());
        } catch (ParseException e) {
            throw new IllegalArgumentException("Expire date is invalid, must be in the format dd/MM/yyyy");
        }


        Date currentDate = new Date();
        String formattedCurrentDate = dateFormat.format(currentDate);

        try {
            currentDate = dateFormat.parse(formattedCurrentDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error while formatting the current date.");
        }


        if (expireDate.before(currentDate)) {
            throw new IllegalArgumentException("Expire date cannot be in the past");
        }
    }
}
