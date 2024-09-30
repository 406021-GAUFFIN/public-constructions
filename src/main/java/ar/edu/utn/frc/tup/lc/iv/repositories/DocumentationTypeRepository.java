package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing DocumentationTypeEntity instances.
 */
@Repository
public interface DocumentationTypeRepository extends JpaRepository<DocumentationTypeEntity, Long> {
}