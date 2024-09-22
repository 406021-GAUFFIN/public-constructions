package ar.edu.utn.frc.tup.lc.iv.models.note;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents a note related to a construction.
 */
@Setter
@Getter
@NoArgsConstructor
public class Note {
    /**
     * Unique identifier for the entity.
     */
    private Long id;

    /**
     * Date and time when the entity was created.
     */
    private LocalDateTime createdDate;

    /**
     * Username of the person who created the entity.
     */
    private String createdBy;

    /**
     * Date and time when the entity was last updated.
     */
    private LocalDateTime lastUpdatedAt;

    /**
     * Username of the person who last updated the entity.
     */
    private String lastUpdatedBy;

    /**
     * Description of the note.
     */
    private String description;

    /**
     * ID of the user who created the note.
     */
    private Long userId;
}
