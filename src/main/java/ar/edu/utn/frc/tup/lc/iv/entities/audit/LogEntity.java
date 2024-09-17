package ar.edu.utn.frc.tup.lc.iv.entities.audit;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity representing a log entry for auditing purposes.
 */
@Entity
@Table(name = LogEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
public class LogEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "LOG";

    /**
     * The content of the log entry.
     */
    @Column(name = "TEXT")
    private String text;



}
