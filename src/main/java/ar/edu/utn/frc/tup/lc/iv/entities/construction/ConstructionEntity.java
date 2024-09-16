package ar.edu.utn.frc.tup.lc.iv.entities.construction;


import ar.edu.utn.frc.tup.lc.iv.dtos.common.enums.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.NoteEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = ConstructionEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class ConstructionEntity extends BaseEntity {
    static final String TABLE_NAME = "CONSTRUCTION";

   @Column(name = "OWNER_ID")
   Long ownerId;

   @Column(name = "LOT_ID")
   Integer lotId;

   @Column(name = "PLANNED_START_DATE")
   Date plannedStartDate;

   @Column(name = "ACTUAL_START_DATE")
   Date actualStartDate;

   @Column(name = "PLANNED_END_DATE")
   Date plannedEndDate;

   @Column(name = "ACTUAL_END_DATE")
   Date actualEndDate;

   @Column(name = "DESCRIPTION")
   String description;

   @Column(name = "APPROVED_BY_MUNICIPALITY")
   Boolean approvedByMunicipality;

   @Column(name = "PROJECT_NAME")
   String projectName;

   @Column(name = "PROJECT_ADDRESS")
   String projectAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "CONSTRUCTION_STATUS")
    private ConstructionStatus constructionStatus;

    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL,orphanRemoval = true)
    List<NoteEntity> notes;

    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL,orphanRemoval = true)
    List<DocumentationConstructionEntity> documentation;

    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL,orphanRemoval = true)
    List<WorkerEntity> workers;
}
