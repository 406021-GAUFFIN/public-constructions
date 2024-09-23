package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.ConstructionDocumentation;
import ar.edu.utn.frc.tup.lc.iv.models.note.Note;
import ar.edu.utn.frc.tup.lc.iv.models.worker.Worker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * This class represents a construction in the system.
 */
@Setter
@Getter
@NoArgsConstructor
public class ConstructionResponseDto {
    /**
     * Unique identifier for the entity.
     */
    private Long id;

    /**
     * ID of the owner of the construction project.
     */
    private Long ownerId;

    /**
     * ID of the plot where the construction takes place.
     */
    private Long plotId;

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
    private List<Note> notes;

    /**
     * List of documentation related to the construction project.
     */
    private List<ConstructionDocumentation> documentation;

    /**
     * List of workers assigned to the construction project.
     */
    private List<Worker> workers;
}
