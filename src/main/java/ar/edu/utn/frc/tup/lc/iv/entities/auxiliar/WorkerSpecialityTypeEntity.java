package ar.edu.utn.frc.tup.lc.iv.entities.auxiliar;


import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;


/**
 * Entity representing different types of worker
 * specializations in a construction project.
 */
@Entity
@Table(name = WorkerSpecialityTypeEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@Audited
public class WorkerSpecialityTypeEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "WORKER_SPECIALITY_TYPE";

    /**
     * Name of the worker specialization.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Description of the worker specialization.
     */
    @Column(name = "DESCRIPTION")
    private String description;



}
