package ar.edu.utn.frc.tup.lc.iv.dtos.construction;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for the response after updating the status of a construction project.
 */
@Setter
@Getter
@NoArgsConstructor
public class ConstructionUpdateStatusResponseDto {

    /**
     * Message indicating the result of the status update operation.
     */
    private String message;
}
