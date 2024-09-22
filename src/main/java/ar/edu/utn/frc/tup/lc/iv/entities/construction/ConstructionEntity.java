package ar.edu.utn.frc.tup.lc.iv.entities.construction;


import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.ConstructionDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.note.NoteEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


/**
 * Entity representing a construction project, with details
 * about its timeline, approval, and related workers, documentation,
 * and notes.
 */
@Entity
@Table(name = ConstructionEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class ConstructionEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "CONSTRUCTION";

    /**
     * ID of the owner of the construction project.
     */

    @Column(name = "OWNER_ID")
    private Long ownerId;

    /**
     * ID of the lot where the construction takes place.
     */
   @Column(name = "LOT_ID")
   private Integer lotId;

    /**
     * Planned start date of the construction.
     */
   @Column(name = "PLANNED_START_DATE")
   private Date plannedStartDate;

    /**
     * Actual start date of the construction.
     */
   @Column(name = "ACTUAL_START_DATE")
   private Date actualStartDate;

    /**
     * Planned end date of the construction.
     */
   @Column(name = "PLANNED_END_DATE")
   private Date plannedEndDate;

    /**
     * Actual end date of the construction, if applicable.
     */
   @Column(name = "ACTUAL_END_DATE")
   private Date actualEndDate;

    /**
     * Description or details about the construction project.
     */
   @Column(name = "DESCRIPTION")
   private String description;

    /**
     * Whether the project has been approved by the municipality.
     */
   @Column(name = "APPROVED_BY_MUNICIPALITY")
   private Boolean approvedByMunicipality;

    /**
     * Name of the construction project.
     */
   @Column(name = "PROJECT_NAME")
   private String projectName;

    /**
     * Address of the construction project.
     */
   @Column(name = "PROJECT_ADDRESS")
   private String projectAddress;

    /**
     * Current status of the construction project
     * (e.g., planned, in progress, completed).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "CONSTRUCTION_STATUS")
    private ConstructionStatus constructionStatus;

    /**
     * List of notes related to the construction project.
     */
    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoteEntity> notes;

    /**
     * List of documentation related to the construction project.
     */
    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConstructionDocumentationEntity> documentation;

    /**
     * List of workers assigned to the construction project.
     */
    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkerEntity> workers;
}
