package ar.edu.utn.frc.tup.lc.iv.entities.documentation;


import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Entity representing documentation related to a worker.
 */
@Entity
@Table(name = WorkerDocumentationEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class WorkerDocumentationEntity extends BaseEntity {
    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "DOCUMENTATION_WORKER";

    /**
     * Expiration date of the documentation.
     */
    @Column(name = "EXPIRE_DATE")
    private Date expireDate;
    /**
     * Path or URL to the ART
     * (Aseguradora de Riesgos del Trabajo) document.
     */
    @Column(name = "ART_DOCUMENT_PATH")
    private String artDocumentPath;

    /**
     * Path or URL to the CAT
     * (Certificado de Alta Temprana) document.
     */
    @Column(name = "CAT_DOCUMENT_PATH")
    private String catDocumentPath;

    /**
     * Worker to whom this documentation belongs.
     */
    @ManyToOne
    @JoinColumn(name = "WORKER_ID", nullable = false)
    private WorkerEntity worker;

}
