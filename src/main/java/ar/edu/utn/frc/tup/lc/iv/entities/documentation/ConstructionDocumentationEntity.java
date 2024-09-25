package ar.edu.utn.frc.tup.lc.iv.entities.documentation;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

/**
 * Entity representing documentation related to a construction project.
 */
@Entity
@Table(name = ConstructionDocumentationEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@Audited

public class ConstructionDocumentationEntity extends BaseEntity {
    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "DOCUMENTATION_CONSTRUCTION";

    /**
     * Indicates whether the documentation has been approved.
     */
    @Column(name = "APPROVED")
    private Boolean approved;

    /**
     * Path or URL to the document.
     */
    @Column(name = "DOCUMENT_PATH")
    private String documentPath;

    /**
     * Type of the documentation (e.g., architectural plan, insurance info).
     */
    @OneToOne
    @JoinColumn(name = "DOCUMENT_TYPE", referencedColumnName = "id")
    private DocumentationTypeEntity documentType;


    /**
     * Construction project to which this documentation belongs.
     */
    @ManyToOne
    @JoinColumn(name = "CONSTRUCTION_ID")
    private ConstructionEntity construction;
}
