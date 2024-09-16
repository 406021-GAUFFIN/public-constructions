package ar.edu.utn.frc.tup.lc.iv.entities.auxiliar;

import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = DocumentationTypeEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class DocumentationTypeEntity extends BaseEntity {

    static final String TABLE_NAME = "DOCUMENTATION_TYPE";

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

}
