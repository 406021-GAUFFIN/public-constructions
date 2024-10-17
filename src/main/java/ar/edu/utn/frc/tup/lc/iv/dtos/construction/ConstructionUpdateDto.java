package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Date plannedStartDate;

    /**
     * Planned end date of the construction.
     */
    @JsonProperty("planned_end_date")
    private Date plannedEndDate;

    /**
     * Description or details about the construction project.
     */
    private String description;

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
}
