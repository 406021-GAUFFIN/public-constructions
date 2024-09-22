package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing construction entities.
 */
public interface ConstructionRepository extends JpaRepository<ConstructionEntity, Long> {

    /**
     * Finds a construction entity by its plot ID.
     *
     * @param plotId The ID of the plot.
     * @return An optional containing
     * the found construction entity, or empty if not found.
     */
    Optional<ConstructionEntity> findByPlotId(Long plotId);
}
