package ar.edu.utn.frc.tup.lc.iv.models.construction;

import ar.edu.utn.frc.tup.lc.iv.entities.documentation.ConstructionDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.note.NoteEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Construction {
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
     * ID of the owner of the construction project.
     */
    private Long ownerId;

    /**
     * ID of the lot where the construction takes place.
     */
    private Integer lotId;

    /**
     * Planned start date of the construction.
     */
    private Date plannedStartDate;

    /**
     * Actual start date of the construction.
     */
    private Date actualStartDate;

    /**
     * Planned end date of the construction.
     */
    private Date plannedEndDate;

    /**
     * Actual end date of the construction, if applicable.
     */
    private Date actualEndDate;

    /**
     * Description or details about the construction project.
     */
    private String description;

    /**
     * Whether the project has been approved by the municipality.
     */
    private Boolean approvedByMunicipality;

    /**
     * Name of the construction project.
     */
    private String projectName;

    /**
     * Address of the construction project.
     */
    private String projectAddress;

    /**
     * Current status of the construction project
     * (e.g., planned, in progress, completed).
     */
    private ConstructionStatus constructionStatus;

    /**
     * List of notes related to the construction project.
     */
    private List<NoteEntity> notes;

    /**
     * List of documentation related to the construction project.
     */
    private List<ConstructionDocumentationEntity> documentation;

    /**
     * List of workers assigned to the construction project.
     */
    private List<WorkerEntity> workers;
}
