package ar.edu.utn.frc.tup.lc.iv.models.worker;

import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerSpecialityEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.WorkerDocumentation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class Worker {
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
     * Contact ID associated with the worker.
     */
    private Integer contactId;

    /**
     * Address of the worker.
     */
    private String address;

    /**
     * Name of the worker.
     */
    private String name;

    /**
     * Last name of the worker.
     */
    private String lastName;

    /**
     * CUIL (Unique Identification Code) of the worker.
     */
    private String cuil;

    /**
     * Document number of the worker.
     */
    private String document;

    /**
     * Type of speciality of the worker.
     */
    private WorkerSpeciality workerSpeciality;

    /**
     * List of documentation associated with the worker.
     */
    private List<WorkerDocumentation> documentation;

    /**
     * Field that indicates if a worker can work on
     * the construction(if worker has unexpired documentation).
     */
    private Boolean availableToWork;
}
