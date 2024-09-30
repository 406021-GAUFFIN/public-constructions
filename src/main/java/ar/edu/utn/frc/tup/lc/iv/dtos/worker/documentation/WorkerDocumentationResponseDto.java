package ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Data Transfer Object for worker documentation responses.
 */
@Setter
@Getter
@NoArgsConstructor
public class WorkerDocumentationResponseDto {


    /**
     * ID of the documentation record.
     */
    private Long id;

    /**
     * Expiration date of the documentation in the format dd/MM/yyyy.
     */
    private String expireDate;

    /**
     * Path or URL to the ART document.
     */
    private String artDocumentationPath;

    /**
     * Path or URL to the CAT document.
     */
    private String catDocumentationPath;

    /**
     * Worker ID to whom this documentation belongs.
     */
    private Long workerId;

    /**
     * Full name of the worker.
     */
    private String workerFullName;
}
