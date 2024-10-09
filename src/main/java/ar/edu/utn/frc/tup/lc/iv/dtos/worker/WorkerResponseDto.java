package ar.edu.utn.frc.tup.lc.iv.dtos.worker;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO for worker response data.
 */
@Setter
@Getter
@NoArgsConstructor
public class WorkerResponseDto {

    /**
     * Unique identifier for the worker.
     */
    private Long id;

    /**
     * Contact information of the worker.
     */
    private ContactRequestDto contact;

    /**
     * Address of the worker.
     */
    private String address;

    /**
     * First name of the worker.
     */
    private String name;

    /**
     * Last name of the worker.
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * CUIL of the worker.
     */
    private String cuil;

    /**
     * Document number of the worker.
     */
    private String document;

    /**
     * Speciality type of the worker.
     */
    @JsonProperty("worker_speciality_type")
    private WorkerSpecialityResponseDto workerSpecialityType;
}
