package ar.edu.utn.frc.tup.lc.iv.entities.documentation;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.envers.Audited;

/**
 * Entity representing the type of documentation used in construction projects.
 */
@Entity
@Table(name = DocumentationTypeEntity.TABLE_NAME)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@NoArgsConstructor
@Audited
public class DocumentationTypeEntity extends BaseEntity {

    /**
     * Name of the table on the database.
     */
    static final String TABLE_NAME = "DOCUMENTATION_TYPE";


    /**
     * Name of the documentation type.
     */
    @Column(name = "NAME")
    private String name;

    /**
     * Description of the documentation type.
     */
    @Column(name = "DESCRIPTION")
    private String description;

}
