package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * This class represents a construction in the system without plotId.
 */
@Setter
@Getter
@NoArgsConstructor
public class ConstructionUpdateDto {

    /**
     * Planned start date of the construction.
     */
    @JsonProperty("planned_start_date")
    @NotNull(message = "Planned start date cannot be null")
    private Date plannedStartDate;

    /**
     * Planned end date of the construction.
     */
    @JsonProperty("planned_end_date")
    @NotNull(message = "Planned end date cannot be null")
    private Date plannedEndDate;

    /**
     * Description or details about the construction project.
     */
    @NotBlank(message = "Description cannot be null or empty")
    private String description;

    /**
     * Name of the construction project.
     */
    @NotBlank(message = "Project name cannot be null or empty")
    @JsonProperty("project_name")
    private String projectName;

    /**
     * Address of the construction project.
     */
    @NotBlank(message = "Address cannot be null or empty")
    @JsonProperty("project_address")
    private String projectAddress;
}
