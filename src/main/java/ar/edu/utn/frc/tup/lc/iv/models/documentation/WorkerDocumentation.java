package ar.edu.utn.frc.tup.lc.iv.models.documentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * This class contains documentation for a worker.
 */
@Setter
@Getter
@NoArgsConstructor
public class WorkerDocumentation {
    /**
     * Unique identifier for the entity.
     */
    private Long id;

    /**
     * Date and time when the entity was created.
     */
    private LocalDateTime createdDate;

    /**
     * Username of the person who created the entity.
     */
    private String createdBy;

    /**
     * Date and time when the entity was last updated.
     */
    private LocalDateTime lastUpdatedAt;

    /**
     * Username of the person who last updated the entity.
     */
    private String lastUpdatedBy;

    /**
     * Expiration date of the documentation.
     */
    private Date expireDate;
    /**
     * Path or URL to the ART
     * (Aseguradora de Riesgos del Trabajo) document.
     */
    private String artDocumentPath;

    /**
     * Path or URL to the CAT
     * (Certificado de Alta Temprana) document.
     */
    private String catDocumentPath;
}
