package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing WorkerEntity instances.
 */
@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long>, JpaSpecificationExecutor<WorkerEntity> {

    /**
     * Finds a worker by their document.
     *
     * @param document The document of the worker.
     * @return An Optional containing the worker if found.
     */
    Optional<WorkerEntity> findWorkerEntityByDocument(String document);

    /**
     * Finds a worker by their CUIL.
     *
     * @param cuil The CUIL of the worker.
     * @return An Optional containing the worker if found.
     */
    Optional<WorkerEntity> findWorkerEntityByCuil(String cuil);

    /**
     * Finds workers whose CUIL contains the specified document.
     *
     * @param document The document to check against CUILs.
     * @return A list of matching workers.
     */
    List<WorkerEntity> findWorkerEntityByCuilContaining(String document);

    /**
     * Retrieves a paginated list of all workers entities.
     *
     * @param pageable The pagination information.
     * @return A page of worker entities.
     */

    Page<WorkerEntity> findAll(Pageable pageable);

    /**
     * Finds a workers entity by its plot ID.
     *
     * @param constructionId The ID of the plot.
     * @return a list of workers asigned to construction.
     * the found construction entity, or empty if not found.
     */
    List<WorkerEntity> findByConstructionId(Long constructionId);
}
