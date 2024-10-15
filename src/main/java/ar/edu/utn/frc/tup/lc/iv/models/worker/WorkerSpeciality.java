package ar.edu.utn.frc.tup.lc.iv.models.worker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents a speciality of a worker.
 */
@Setter
@Getter
@NoArgsConstructor
public class WorkerSpeciality {
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
     * Name of the worker specialization.
     */
    private String name;

    /**
     * Description of the worker specialization.
     */
    private String description;
}
