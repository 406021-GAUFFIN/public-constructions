package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.ConstructionDocumentation;
import ar.edu.utn.frc.tup.lc.iv.models.note.Note;
import ar.edu.utn.frc.tup.lc.iv.models.worker.Worker;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
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
     * Constant for date format to avoid duplicate literals.
     */
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Unique identifier for the entity.
     */
    @JsonProperty("construction_id")
    private Long id;

    /**
     * ID of the plot where the construction takes place.
     */
    @JsonProperty("plot_id")
    private Long plotId;

    /**
     * Planned start date of the construction.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty("planned_start_date")
    private Date plannedStartDate;

    /**
     * Actual start date of the construction.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty("actual_start_date")
    private Date actualStartDate;

    /**
     * Planned end date of the construction.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty("planned_end_date")
    private Date plannedEndDate;

    /**
     * Actual end date of the construction, if applicable.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT)
    @JsonProperty("actual_end_date")
    private Date actualEndDate;

    /**
     * Description or details about the construction project.
     */
    @JsonProperty("project_description")
    private String description;

    /**
     * Whether the project has been approved by the municipality.
     */
    @JsonProperty("approved_by_municipality")
    private Boolean approvedByMunicipality;

    /**
     * Name of the construction project.
     */
    @JsonProperty("project_name")
    private String projectName;

    /**
     * Address of the construction project.
     */
    @JsonProperty("project_address")
    private String projectAddress;

    /**
     * Current status of the construction project
     * (e.g., planned, in progress, completed).
     */
    @JsonProperty("construction_status")
    private ConstructionStatus constructionStatus;

    /**
     * List of notes related to the construction project.
     */
    @JsonProperty("notes")
    private List<Note> notes;

    /**
     * List of documentation related to the construction project.
     */
    @JsonProperty("construction_documentation")
    private List<ConstructionDocumentation> documentation;

    /**
     * List of workers assigned to the construction project.
     */
    @JsonProperty("workers")
    private List<Worker> workers;
}
