package ar.edu.utn.frc.tup.lc.iv.models.construction;


import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.UpdateConstructionStatusException;

import java.util.Date;

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
    STOPPED;

    public void validateTransition(ConstructionEntity constructionEntity) {
        switch (this) {
            case APPROVED:
                if (!constructionEntity.getApprovedByMunicipality() || constructionEntity.getWorkers().isEmpty()) {
                    throw new UpdateConstructionStatusException("The construction is not valid for approval.");
                }
                break;



            case COMPLETED:
                if (constructionEntity.getConstructionStatus() != IN_PROGRESS) {
                    throw new UpdateConstructionStatusException("The construction cannot be marked as COMPLETED because it is not in the IN_PROGRESS state.");
                }
                break;

            default:
                break;
        }
    }
    public void handleStateTransition(ConstructionEntity constructionEntity) {
        switch (this) {
            case IN_PROGRESS:
                constructionEntity.setActualStartDate(new Date());
                break;

            case COMPLETED:
                constructionEntity.setActualEndDate(new Date());
                break;

            default:
                break;
        }
    }

}
