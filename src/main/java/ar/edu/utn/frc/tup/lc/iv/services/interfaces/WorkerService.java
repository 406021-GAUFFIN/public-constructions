package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * Retrieves all workers without pagination.
     *
     * @return a list of response DTOs for all workers
     */
    List<WorkerResponseDto> getAllWorkers();

    /**
     * Retrieves a paginated list of workers.
     *
     * @param pageable the pagination information
     * @return a page of worker response DTOs
     */
    Page<WorkerResponseDto> getAllWorkersPage(Pageable pageable);

    /**
     * Get all workers of construction.
     * @param constructionId the unique identifier of the construction project
     * @return a list of worker response DTOs for the specified construction
     */
    List<WorkerResponseDto> getAllWorkersOfConstruction(Long constructionId);

    /**
     * Unassigns a worker from a construction.
     *
     * @param workerId the ID of the worker to unassign.
     */
    void unassignWorkerFromConstruction(Long workerId);

    /**
     * Assigns a worker to a construction.
     *
     * @param workerId the ID of the worker.
     * @param constructionId the ID of the construction.
     */
    void assignWorkerToConstruction(Long workerId, Long constructionId);

    /**
     * Deletes a worker from the system.
     *
     * @param workerId the ID of the worker to delete.
     */
    void deleteWorker(Long workerId);
}
