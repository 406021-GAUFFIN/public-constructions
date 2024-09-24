package ar.edu.utn.frc.tup.lc.iv.entities.note;


import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

/**
 * Entity representing a note associated
 * with a construction project.
 */
@Entity
@Table(name = NoteEntity.TABLE_NAME)
@Setter
@Getter
@NoArgsConstructor
@Audited
public class NoteEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final  String TABLE_NAME = "NOTE";

    /**
     * Description of the note.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * ID of the user who created the note.
     */
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * The construction project associated with the note.
     */
    @ManyToOne
    @JoinColumn(name = "CONSTRUCTION_ID")
    private ConstructionEntity construction;


}
