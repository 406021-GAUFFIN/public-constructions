package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponse;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.RegisterAuthorizationRangesDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import org.springframework.stereotype.Service;



/**
 * Service interface for managing workers operations.
 */
@Service
public interface WorkerService {
    /**
     * Creates a new worker based on the
     * provided worker request data.
     * @param workerRequestDto the worker data to create
     * @return the created worker's response DTO
     */
    WorkerResponseDto createWorker(WorkerRequestDto workerRequestDto);
    WorkerDocumentationResponseDto addWorkerDocumentation(WorkerDocumentationRequestDto workerDocumentationRequestDto);
    AuthorizationRangeResponse allowWorkerAccess(Long workerId, String comment);


}
