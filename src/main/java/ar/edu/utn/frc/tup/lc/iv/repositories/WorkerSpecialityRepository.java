package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerSpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing WorkerSpecialityEntity instances.
 */
@Repository
public interface WorkerSpecialityRepository extends JpaRepository<WorkerSpecialityEntity, Long> {
}
