package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * This class represents a construction in the system.
 */
@Setter
@Getter
@NoArgsConstructor
public class ConstructionRequestDto {
    /**
     * ID of the owner of the construction project.
     */
    @JsonProperty("owner-id")
    private Long ownerId;

    /**
     * ID of the plot where the construction takes place.
     */
    @JsonProperty("plot-id")
    private Long plotId;

    /**
     * Planned start date of the construction.
     */
    @JsonProperty("planned-start-date")
    private Date plannedStartDate;

    /**
     * Planned end date of the construction.
     */
    @JsonProperty("planned-end-date")
    private Date plannedEndDate;

    /**
     * Description or details about the construction project.
     */
    private String description;

    /**
     * Name of the construction project.
     */
    @JsonProperty("project-name")
    private String projectName;

    /**
     * Address of the construction project.
     */
    @JsonProperty("project-address")
    private String projectAddress;
}
