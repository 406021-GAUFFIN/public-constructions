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

/**
 * DTO for worker creation requests.
 */
@NoArgsConstructor
@Setter
@Getter
public class WorkerRequestDto {

    /**
     * Maximum length for the worker's name.
     */
    private static final int MAX_NAME_LENGTH = 50;

    /**
     * Maximum length for the worker's last name.
     */
    private static final int MAX_LAST_NAME_LENGTH = 50;

    /**
     * Maximum length for the worker's document number.
     */
    private static final int MAX_DOCUMENT_LENGTH = 20;
    /**
     * Contact information for the worker.
     */
    @NotNull(message = "Contact information is required.")
    private ContactRequestDto contact;

    /**
     * Address of the worker.
     */
    @NotBlank(message = "Address is required.")
    private String address;

    /**
     * First name of the worker.
     */
    @NotBlank(message = "Name is required.")
    @Size(max = MAX_NAME_LENGTH, message = "Name cannot exceed " + MAX_NAME_LENGTH + " characters.")
    private String name;

    /**
     * Last name of the worker.
     */
    @NotBlank(message = "Last name is required.")
    @Size(max = MAX_LAST_NAME_LENGTH, message = "Last name cannot exceed " + MAX_LAST_NAME_LENGTH + " characters.")
    @JsonProperty("last_name")
    private String lastName;

    /**
     * CUIL of the worker, must be exactly 11 digits.
     */
    @NotBlank(message = "CUIL is required.")
    @Pattern(regexp = "\\d{11}", message = "CUIL must be exactly 11 digits.")
    private String cuil;

    /**
     * Document number of the worker.
     */
    @NotBlank(message = "Document is required.")
    @Size(max = MAX_DOCUMENT_LENGTH, message = "Document cannot exceed " + MAX_DOCUMENT_LENGTH + " characters.")
    private String document;

    /**
     * ID of the worker's specialty type.
     */
    @JsonProperty("worker_speciality_type")
    private Long workerSpecialityType;

    /**
     * ID of the construction associated with the worker.
     */
    @JsonProperty("construction_id")
    private Long constructionId;

    /**
     * ID of the user who created this worker record.
     */
    @JsonProperty("created_by")
    private Long createdBy;
}
