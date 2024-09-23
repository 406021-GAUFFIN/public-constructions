package ar.edu.utn.frc.tup.lc.iv.controllers.construction;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing construction operations.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/constructions")
@CrossOrigin(origins = "*")
public class ConstructionController {

    /**
     * Service for handling construction logic.
     */
    private final ConstructionService constructionService;

    /**
     * Creates a new construction based on the provided request DTO.
     *
     * @param constructionRequestDto The data transfer object
     *                               containing construction details.
     * @return The response DTO of the created construction.
     */
    @PostMapping
    public ResponseEntity<ConstructionResponseDto> createConstruction(@RequestBody ConstructionRequestDto constructionRequestDto) {
        return ResponseEntity.ok(constructionService.registerConstruction(constructionRequestDto));
    }
}
