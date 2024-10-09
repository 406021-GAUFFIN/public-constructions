package ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Data Transfer Object for authorization response.
 */

@NoArgsConstructor
@Setter
@Getter
public class AuthorizationRangeResponseDto {
    /**
     * The ID of the user who created the entity.
     */
    private Long createdUser;

    /**
     * The date and time when the entity was created.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    /**
     * The ID of the user who last updated the entity.
     */
    private Long lastUpdatedUser;

    /**
     * The date and time when the entity was last updated.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastUpdatedDate;

    /**
     * Unique identifier of the Acceses.
     */
    private Long authRangeId;

    /**
     * Unique Authorized type identifier.
     */
    private Long authType;

    /**
     * Unique Authorized identifier.
     */
    private Long visitorId;

    /**
     * External ID to identify Suppliers, Employees, Owners and Cohabitants.
     */
    private Long externalId;

    /**
     * Date from to form the authorized range.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateFrom;

    /**
     * Date until to form the authorized range.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateTo;

    /**
     * Starting time for the authorized range on each day.
     * Defines the hour from which access is allowed.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime hourFrom;

    /**
     * Ending time for the authorized range on each day.
     * Defines the hour until which access is allowed.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime hourTo;

    /**
     * Days of the week for which the authorization is valid (Monday, Wednesday).
     */
    private String days;

    /**
     * Unique plot identifier.
     */
    private Long plotId;

    /**
     * Additional comments or observations related to the authorization.
     */
    private String comment;

    /**
     * Indicates whether the authorization is currently active.
     */
    private boolean isActive;
}
