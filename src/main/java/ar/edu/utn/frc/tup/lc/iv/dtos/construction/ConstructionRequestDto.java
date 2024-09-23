package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

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
     * Planned end date of the construction.
     */
    private Date plannedEndDate;

    /**
     * Description or details about the construction project.
     */
    private String description;

    /**
     * Name of the construction project.
     */
    private String projectName;

    /**
     * Address of the construction project.
     */
    private String projectAddress;
}
