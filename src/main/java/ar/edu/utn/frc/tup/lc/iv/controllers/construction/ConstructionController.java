package ar.edu.utn.frc.tup.lc.iv.controllers.construction;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


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
    @Operation(
            summary = "Create a new construction",
            description = "Creates a new construction by providing the necessary details, such as plot ID and other construction details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Construction created successfully",
                    content = @Content(
                            schema = @Schema(implementation = ConstructionResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid construction request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<ConstructionResponseDto> createConstruction(@RequestBody ConstructionRequestDto constructionRequestDto) {
        return ResponseEntity.ok(constructionService.registerConstruction(constructionRequestDto));
    }


    /**
     * Update the status of a construction.
     *
     * @param constructionUpdateStatusRequestDto request data containing
     *                                           construction ID and new status
     * @return Response containing the updated construction status
     */
    @Operation(
            summary = "Update construction status",
            description = "Update the status of an existing construction by providing a valid status and construction ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Construction status updated successfully",
                    content = @Content(
                            schema = @Schema(implementation = ConstructionResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Construction not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid status or validation failure",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PutMapping("/status")
    public ConstructionResponseDto updateConstructionStatus(
            @RequestBody @Valid ConstructionUpdateStatusRequestDto constructionUpdateStatusRequestDto) {
        constructionUpdateStatusRequestDto.setConstructionId(constructionUpdateStatusRequestDto.getConstructionId());
        return constructionService.updateConstructionStatus(constructionUpdateStatusRequestDto);
    }

}
