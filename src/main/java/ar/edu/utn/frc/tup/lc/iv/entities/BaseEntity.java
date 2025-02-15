package ar.edu.utn.frc.tup.lc.iv.entities;



import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Integer createdBy;

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
    private Integer lastUpdatedBy;
    /**
     * Sets the creation date to the current date
     * and time before the entity is persisted.
     */
    @PrePersist
    public void setCreatedDate() {
        this.createdDate = LocalDateTime.now();
    }

    /**
     * Updates the last updated date to the current date
     * and time before the entity is updated.
     */
    @PreUpdate
    public void setLastUpdatedAt() {
        this.lastUpdatedAt = LocalDateTime.now();
    }




}
