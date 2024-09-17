package ar.edu.utn.frc.tup.lc.iv.entities;


import ar.edu.utn.frc.tup.lc.iv.entities.audit.LogEntity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;


/**
 * Base entity class providing common fields
 * and behavior for all entities.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    /**
     * Unique identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Date and time when the entity was created.
     */
    @CreatedDate
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    /**
     * Username of the person who created the entity.
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * Date and time when the entity was last updated.
     */
    @LastModifiedDate
    @Column(name = "LAST_UPDATED_AT")
    private LocalDateTime lastUpdatedAt;

    /**
     * Username of the person who last updated the entity.
     */
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;


    /**
     * Transient field to hold the username for logging purposes.
     */
    @Transient
    private String userName;

    /**
     * Transient field to hold log information for the entity.
     */
    @Transient
    private String log;


    /**
     * Hook method that is called before the entity is persisted.
     * Creates a log entry if logging is enabled.
     */
    @PrePersist
    protected void onCreate() {
        if (shouldLog()) {
            createLog(this.userName, this.log);
        }

    }

    /**
     * Hook method that is called before the entity is updated.
     * Creates a log entry if logging is enabled.
     */
    @PreUpdate
    protected void onUpdate() {
        if (shouldLog()) {
            createLog(this.userName, this.log);
        }
    }

    /**
     * Creates a log entry for the entity.
     * @param userNameParam the username associated with
     *                 the log entry
     * @param logParam the log message
     */
    private void createLog(String userNameParam, String logParam) {
        System.out.println(userNameParam + ": " + logParam);
        throw new NotImplementedException();
    }

    /**
     * Determines if logging should be performed.
     * Logging is not performed for instances of {@link LogEntity}.
     *
     * @return true if logging should be performed; false otherwise
     */
    private boolean shouldLog() {
        return  !(this instanceof LogEntity);
    }

}
