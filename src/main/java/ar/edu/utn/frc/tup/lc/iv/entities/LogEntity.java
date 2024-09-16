package ar.edu.utn.frc.tup.lc.iv.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = LogEntity.TABLE_NAME )
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class LogEntity extends BaseEntity {

    static final String TABLE_NAME = "LOG";

    @Column(name = "TEXT")
    String text;



}
