package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing worker operations.
 */
@Service
public interface WorkerService {

    /**
     * Creates a new worker.
     *
     * @param workerRequestDto the worker data
     * @return the created worker's response DTO
     */
    WorkerResponseDto createWorker(WorkerRequestDto workerRequestDto);

    /**
     * Adds documentation for a worker.
     *
     * @param workerDocumentationRequestDto the documentation data
     * @return the response DTO for the added documentation
     */
    WorkerDocumentationResponseDto addWorkerDocumentation(WorkerDocumentationRequestDto workerDocumentationRequestDto);

    /**
     * Allows access for a worker with a comment.
     *
     * @param workerId the worker's ID
     * @param comment the access comment
     * @return the response DTO indicating access status
     */
    AuthorizationRangeResponseDto allowWorkerAccess(Long workerId, String comment);
}
