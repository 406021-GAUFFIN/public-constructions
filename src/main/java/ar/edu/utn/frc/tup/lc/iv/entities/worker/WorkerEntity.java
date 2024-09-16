package ar.edu.utn.frc.tup.lc.iv.entities.worker;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.audit.LogEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.DocumentationTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.auxiliar.WorkerSpecialityTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationWorkerEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = WorkerEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class WorkerEntity extends BaseEntity {

    static final String TABLE_NAME = "WORKER";

    @Column(name = "CONTACT_ID")
    Integer contactId;

    @Column(name = "ADDRESS")
    String address;

    @Column(name = "NAME")
    String name;

    @Column(name = "LAST_NAME")
    String lastName;

    @Column(name = "CUIL")
    String cuil;

    @Column(name = "DOCUMENT")
    String document;

    @OneToOne
    @JoinColumn(name = "SPECIALITY_TYPE", referencedColumnName = "id")
    private WorkerSpecialityTypeEntity workerSpecialityType;


    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL,orphanRemoval = true)
    List<DocumentationWorkerEntity> documentationWorker;

    @ManyToOne
    @JoinColumn(name = "CONSTRUCTION_ID")
    ConstructionEntity construction;
}
