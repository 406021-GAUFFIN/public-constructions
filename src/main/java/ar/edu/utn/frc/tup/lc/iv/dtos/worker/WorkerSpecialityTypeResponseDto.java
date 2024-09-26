package ar.edu.utn.frc.tup.lc.iv.dtos.worker;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WorkerSpecialityTypeResponseDto {


    /**
     * Unique identifier for the entity.
     */
    private Long id;
    /**
     * Name of the worker specialization.
     */
    private String name;

    /**
     * Description of the worker specialization.
     */
    private String description;
}
