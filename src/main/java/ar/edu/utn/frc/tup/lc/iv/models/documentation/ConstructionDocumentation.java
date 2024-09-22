package ar.edu.utn.frc.tup.lc.iv.models.documentation;

import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationTypeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ConstructionDocumentation {
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
     * Indicates whether the documentation has been approved.
     */
    private Boolean approved;

    /**
     * Path or URL to the document.
     */
    private String documentPath;

    /**
     * Type of the documentation (e.g., architectural plan, insurance info).
     */
    private DocumentationTypeEntity documentType;
}
