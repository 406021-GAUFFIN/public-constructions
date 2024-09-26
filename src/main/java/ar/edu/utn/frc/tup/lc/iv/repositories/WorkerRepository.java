package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing WorkerEntity instances.
 */
@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {

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
     * Finds workers whose document contains the specified CUIL.
     *
     * @param cuil The CUIL to check against documents.
     * @return A list of matching workers.
     */
    List<WorkerEntity> findWorkerEntityByDocumentContaining(String cuil);
}
