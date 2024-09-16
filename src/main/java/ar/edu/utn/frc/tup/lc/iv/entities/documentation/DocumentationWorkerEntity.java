package ar.edu.utn.frc.tup.lc.iv.entities.documentation;


import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.audit.LogEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Table(name = DocumentationWorkerEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class DocumentationWorkerEntity extends BaseEntity {
    static final String TABLE_NAME = "DOCUMENTATION_WORKER";

    @Column(name = "EXPIRE_DATE")
    Date expireDate;

    @Column(name = "ART_DOCUMENT_PATH")
    String artDocumentPath;

    @Column(name = "CAT_DOCUMENT_PATH")
    String catDocumentPath;

    @ManyToOne
    @JoinColumn(name = "WORKER_ID", nullable = false)
    WorkerEntity worker;

}
