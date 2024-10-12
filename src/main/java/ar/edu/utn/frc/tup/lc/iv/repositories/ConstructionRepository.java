package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Repository interface for accessing construction entities.
 */
public interface ConstructionRepository extends JpaRepository<ConstructionEntity, Long>, JpaSpecificationExecutor<ConstructionEntity> {

    /**
     * Finds a construction entity by its plot ID.
     *
     * @param plotId The ID of the plot.
     * @return An optional containing
     * the found construction entity, or empty if not found.
     */
    Optional<ConstructionEntity> findByPlotId(Long plotId);

    /**
     * Retrieves a paginated list of all construction entities.
     *
     * @param pageable The pagination information.
     * @return A page of construction entities.
     */

    Page<ConstructionEntity> findAll(Pageable pageable);

    /**
     * Retrieves a paginated list of construction entities based on a filter.
     *
     * @param filter The specification filter.
     * @param pageable The pagination information.
     * @return A page of construction entities that match the filter.
     */

    Page<ConstructionEntity> findAll(Specification<ConstructionEntity> filter, Pageable pageable);

}
