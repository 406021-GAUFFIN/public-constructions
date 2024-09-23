package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service interface for managing construction operations.
 */
@Service
public interface ConstructionService {

    /**
     * Registers a new construction based on the provided request DTO.
     *
     * @param constructionRequest The data transfer object
     *                            containing construction details.
     * @return The response DTO of the registered construction.
     */
    ConstructionResponseDto registerConstruction(ConstructionRequestDto constructionRequest);
}
