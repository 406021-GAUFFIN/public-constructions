package ar.edu.utn.frc.tup.lc.iv.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    LocalDateTime createdDate;

    @Column(name = "CREATED_BY")
    String createdBy;

    @LastModifiedDate
    @Column(name = "LAST_UPDATED_AT")
    LocalDateTime lastUpdatedAt;

    @Column(name = "LAST_UPDATED_BY")
    String lastUpdatedBy;

    @Transient
    String userName;

    @Transient
    String log;

    @PrePersist
    protected void onCreate() {
        createLog(this.userName,this.log);
    }
    @PreUpdate
    protected void onUpdate() {
        createLog(this.userName,this.log);
    }

    private void createLog(String userName, String log) {
        //TODO: IMPLEMENT LOGGING SAVING ON THE TABLE LOG
        throw new NotImplementedException();
    }

}
