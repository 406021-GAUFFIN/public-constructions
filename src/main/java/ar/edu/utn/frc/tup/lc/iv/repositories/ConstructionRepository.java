package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConstructionRepository extends JpaRepository<ConstructionEntity, Long> {
    Optional<ConstructionEntity> findByPlotId(Long plotId);
}