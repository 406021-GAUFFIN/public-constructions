package ar.edu.utn.frc.tup.lc.iv.entities.construction;


import ar.edu.utn.frc.tup.lc.iv.entities.BaseEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.audit.LogEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = NoteEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class NoteEntity extends BaseEntity {

    static final  String TABLE_NAME = "NOTE";

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "USER_ID")
    Long userId;


}
