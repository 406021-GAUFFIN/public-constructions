package ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for worker documentation requests.
 * This class is used to encapsulate the information required to add
 * documentation for a specific worker, including expiration dates
 * and document paths.
 */
@Setter
@Getter
@NoArgsConstructor
public class WorkerDocumentationRequestDto {

    /**
     * Expiration date of the documentation, in the format dd/MM/yyyy.
     */
    @NotNull(message = "Expire date is required")
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}", message = "Expire date must be in the format dd/MM/yyyy")
    @JsonProperty("expire_date")
    private String expireDate;

    /**
     * Path or URL to the ART document.
     */
    @NotNull(message = "ART document path is required")
    @JsonProperty("art_documentation_path")
    private String artDocumentationPath;

    /**
     * Path or URL to the CAT document.
     */
    @NotNull(message = "CAT document path is required")
    @JsonProperty("cat_documentation_path")
    private String catDocumentationPath;

    /**
     * ID of the worker to whom this documentation belongs.
     */
    @NotNull(message = "Worker ID is required")
    @JsonProperty("worker_id")
    private Long workerId;

    /**
     * ID of the user who created this documentation entry.
     */
    @NotNull(message = "created_by is required")
    @JsonProperty("created_by")
    private Integer createdBy;
}
