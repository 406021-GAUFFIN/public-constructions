package ar.edu.utn.frc.tup.lc.iv.entities.worker;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

/**
 * Entity representing a worker in the construction project.
 */
@Entity
@Table(name = WorkerEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@Audited
public class WorkerEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "WORKER";

    /**
     * Contact ID associated with the worker.
     */
    @Column(name = "CONTACT_ID")
    private Integer contactId;

    /**
     * Address of the worker.
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * Name of the worker.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Last name of the worker.
     */
    @Column(name = "LAST_NAME")
    private String lastName;

    /**
     * CUIL (Unique Identification Code) of the worker.
     */
    @Column(name = "CUIL")
    private String cuil;

    /**
     * Document number of the worker.
     */
    @Column(name = "DOCUMENT")
    private String document;

    /**
     * Type of speciality of the worker.
     */
    @OneToOne
    @JoinColumn(name = "SPECIALITY_TYPE", referencedColumnName = "id")
    private WorkerSpecialityEntity workerSpecialityType;

    /**
     * List of documentation associated with the worker.
     */
    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<WorkerDocumentationEntity> documentationWorker;

    /**
     * Construction project to which the worker is assigned.
     */
    @ManyToOne
    @JoinColumn(name = "CONSTRUCTION_ID")
    private ConstructionEntity construction;

    /**
     * Field that indicates if a worker can work on
     * the construction(if worker has unexpired documentation).
     */
    @Column(name = "AVAIBLE_TO_WORK")
    private Boolean availableToWork;
}
