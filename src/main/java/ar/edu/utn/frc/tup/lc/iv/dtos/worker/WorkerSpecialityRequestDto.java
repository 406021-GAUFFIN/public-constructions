package ar.edu.utn.frc.tup.lc.iv.dtos.worker;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for worker specialization type request data.
 */
@Setter
@Getter
@NoArgsConstructor
public class WorkerSpecialityRequestDto {

    /**
     * Name of the worker specialization.
     */
    private String name;

    /**
     * Description of the worker specialization.
     */
    private String description;
}
