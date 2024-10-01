package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing construction operations.
 */
@Service
public interface ConstructionService {

    /**
     * Registers a new construction.
     *
     * @param constructionRequest DTO with construction details.
     * @return Response DTO of the registered construction.
     */
    ConstructionResponseDto registerConstruction(ConstructionRequestDto constructionRequest);

    /**
     * Updates the status of an existing construction.
     *
     * @param constructionUpdateStatusRequestDto
     * DTO with construction ID and new status.
     * @return Response DTO indicating the status update result.
     */
    ConstructionResponseDto updateConstructionStatus(ConstructionUpdateStatusRequestDto constructionUpdateStatusRequestDto);

}
