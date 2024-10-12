package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
     * @return Response DTO indicating the status update result
     */
    ConstructionResponseDto updateConstructionStatus(ConstructionUpdateStatusRequestDto constructionUpdateStatusRequestDto);

    /**
     * Retrieves all constructions.
     *
     * @return A list of construction response DTOs.
     */
    List<ConstructionResponseDto> getAllConstructions();

    /**
     * Retrieves a construction by its ID.
     *
     * @param id The ID of the construction.
     * @return The response DTO of the construction.
     */
    ConstructionResponseDto getConstructionById(Long id);

    /**
     * Retrieves a paginated list of constructions with optional filtering by status.
     *
     * @param pageable Pagination information.
     * @param constructionStatus List of construction statuses to filter by.
     * @return A pageable list of construction request DTOs.
     */
    Page<ConstructionRequestDto> getAllConstructionsPage(Pageable pageable, List<ConstructionStatus> constructionStatus);
}
