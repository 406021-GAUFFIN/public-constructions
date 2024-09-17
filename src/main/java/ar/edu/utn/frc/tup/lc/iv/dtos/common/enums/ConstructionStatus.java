package ar.edu.utn.frc.tup.lc.iv.dtos.common.enums;


/**
 * Enum representing the various statuses a construction project can have
 * throughout its lifecycle.
 */
public enum ConstructionStatus {
    /**
     * Indicates that the construction project is in
     * the planning stage.
     * No actual construction work has begun.
     */
    PLANNED,

    /**
     * Indicates that the construction project has been approved
     * by the necessary regulatory or municipal authorities.
     */
    APPROVED,

    /**
     * Indicates that the construction project is actively under development.
     */
    IN_PROGRESS,

    /**
     * Indicates that the construction project is
     * completed and all work has been finalized.
     */
    COMPLETED,

    /**
     * Indicates that the construction project
     * has been stopped, either temporarily or permanently.
     */
    STOPPED
}
