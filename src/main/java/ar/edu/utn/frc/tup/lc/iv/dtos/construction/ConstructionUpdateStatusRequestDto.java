package ar.edu.utn.frc.tup.lc.iv.dtos.construction;

import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for updating the status of a construction project.
 */
@Setter
@Getter
@NoArgsConstructor
public class ConstructionUpdateStatusRequestDto {

    /**
     * Unique identifier for the construction project.
     */
    @JsonProperty("construction-id")
    private Long constructionId;

    /**
     * New status of the construction project.
     */
    private ConstructionStatus status;
}
