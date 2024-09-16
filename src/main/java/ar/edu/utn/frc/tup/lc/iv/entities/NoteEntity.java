package ar.edu.utn.frc.tup.lc.iv.entities;


import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "CONSTRUCTION_ID")
    ConstructionEntity construction;


}
