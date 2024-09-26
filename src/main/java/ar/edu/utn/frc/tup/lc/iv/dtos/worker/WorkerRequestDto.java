package ar.edu.utn.frc.tup.lc.iv.dtos.worker;

import ar.edu.utn.frc.tup.lc.iv.dtos.external.ContactRequestDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class WorkerRequestDto {

    @NotNull(message = "Contact information is required.")
    private ContactRequestDto contact;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotBlank(message = "Name is required.")
    @Size(max = 50, message = "Name cannot exceed 50 characters.")
    private String name;

    @NotBlank(message = "Last name is required.")
    @Size(max = 50, message = "Last name cannot exceed 50 characters.")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "CUIL is required.")
    @Pattern(regexp = "\\d{11}", message = "CUIL must be exactly 11 digits.")
    private String cuil;

    @NotBlank(message = "Document is required.")
    @Size(max = 20, message = "Document cannot exceed 20 characters.")
    private String document;

    @JsonProperty("worker_speciality_type")
    private Long workerSpecialityType;

    @JsonProperty("construction_id")
    private Long constructionId;

    @JsonProperty("created_by")
    private Long createdBy;



}
