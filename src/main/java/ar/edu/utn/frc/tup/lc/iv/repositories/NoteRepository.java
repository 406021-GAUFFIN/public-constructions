package ar.edu.utn.frc.tup.lc.iv.repositories;

import ar.edu.utn.frc.tup.lc.iv.entities.note.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing NoteEntity instances.
 */
@Repository
public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
}
