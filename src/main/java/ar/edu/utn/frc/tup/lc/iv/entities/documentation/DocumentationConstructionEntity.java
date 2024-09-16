package ar.edu.utn.frc.tup.lc.iv.entities.documentation;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.DocumentationTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = DocumentationConstructionEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class DocumentationConstructionEntity extends BaseEntity {
    static final String TABLE_NAME = "DOCUMENTATION_CONSTRUCTION";

    @Column(name = "APPROVED")
    Boolean approved;

    @Column(name = "DOCUMENT_PATH")
    String documentPath;

    @OneToOne
    @JoinColumn(name = "DOCUMENT_TYPE", referencedColumnName = "id")
    private DocumentationTypeEntity documentType;

    @ManyToOne
    @JoinColumn(name = "CONSTRUCTION_ID")
    private ConstructionEntity construction;
}
