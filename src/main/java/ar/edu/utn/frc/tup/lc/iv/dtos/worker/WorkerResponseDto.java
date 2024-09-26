package ar.edu.utn.frc.tup.lc.iv.dtos.worker;


import ar.edu.utn.frc.tup.lc.iv.dtos.external.ContactRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WorkerResponseDto {

    private Long id;

    private ContactRequestDto contact;


    private String address;


    private String name;


    @JsonProperty("last_name")
    private String lastName;


    private String cuil;


    private String document;

    @JsonProperty("worker_speciality_type")
    private WorkerSpecialityTypeResponseDto workerSpecialityType;
}
