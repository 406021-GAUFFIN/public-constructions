package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing worker documentation.
 * This interface defines methods for creating and
 * retrieving worker documentation.
 */
@Service
public interface WorkerDocumentationService {

    /**
     * Creates a new worker documentation entry based on
     * the provided request DTO.
     *
     * @param requestDto the request data transfer object
     *                  containing documentation details
     * @return the created WorkerDocumentationEntity
     */
    WorkerDocumentationEntity createWorkerDocumentation(WorkerDocumentationRequestDto requestDto);

    /**
     * Retrieves the latest documentation for a worker by their ID.
     *
     * @param workerId the ID of the worker whose documentation is to be retrieved
     * @return the latest WorkerDocumentationEntity for the specified worker
     */
    WorkerDocumentationEntity getLatestWorkerDocumentation(Long workerId);
}
