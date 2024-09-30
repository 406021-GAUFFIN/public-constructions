package ar.edu.utn.frc.tup.lc.iv.controllers.documentation;

import ar.edu.utn.frc.tup.lc.iv.controllers.api_response.ApiResponseConstants;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.documentation.DocumentationTypeRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.DocumentationType;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.DocumentationTypeService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


/**
 * Controller for managing documentation type operations.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/documentation-types")
@CrossOrigin(origins = "*")
public class DocumentationTypeController {
    /**
     * Service for handling documentation type logic.
     */
    private final DocumentationTypeService documentationTypeService;

    /**
     * Retrieves a list of all documentation types.
     *
     * @return A list of documentation type.
     */
    @Operation(
            summary = "Get all documentation types",
            description = "Retrieves a list of all documentation types available"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = DocumentationType.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<DocumentationType>> getDocumentationTypeList() {
        return ResponseEntity.ok(documentationTypeService.getDocumentationTypeList());
    }

    /**
     * Retrieves a documentation type by its ID.
     *
     * @param id The ID of the documentation type to retrieve.
     * @return The documentation type.
     */
    @Operation(
            summary = "Get a documentation type by ID",
            description = "Retrieves a specific documentation type based on its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = DocumentationType.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.NOT_FOUND,
                    description = ApiResponseConstants.NOT_FOUND_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<DocumentationType> getDocumentationTypeById(@PathVariable Long id) {
        return ResponseEntity.ok(documentationTypeService.getDocumentationTypeById(id));
    }

    /**
     * Creates a new documentation type based on the provided request DTO.
     *
     * @param saveDocumentationType The data transfer object
     *                              containing documentation type details.
     * @return The created documentation type.
     */
    @Operation(
            summary = "Create a new documentation type",
            description = "Creates a new documentation type by providing the necessary details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.CREATED,
                    description = ApiResponseConstants.CREATED_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = DocumentationTypeRequestDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.BAD_REQUEST,
                    description = ApiResponseConstants.BAD_REQUEST_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.CONFLICT,
                    description = ApiResponseConstants.CONFLICT_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<DocumentationType> createDocumentationType(@RequestBody @Valid DocumentationTypeRequestDto
                                                                                 saveDocumentationType) {
        return ResponseEntity.ok(documentationTypeService.createDocumentationType(saveDocumentationType));
    }

    /**
     * Update the status of a documentation type.
     * @param id the ID of the documentation type to be updated
     * @param saveDocumentationType request data containing
     *                              documentation type ID and new details
     * @return Response containing the updated documentation type.
     */
    @Operation(
            summary = "Update documentation type",
            description = "Update documentation type by providing the necessary details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ConstructionUpdateStatusResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.NOT_FOUND,
                    description = ApiResponseConstants.NOT_FOUND_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.BAD_REQUEST,
                    description = ApiResponseConstants.BAD_REQUEST_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<DocumentationType> updateDocumentationType(@PathVariable Long id,
                                                                     @RequestBody DocumentationTypeRequestDto saveDocumentationType) {
        return ResponseEntity.ok(documentationTypeService.updateDocumentationType(id, saveDocumentationType));
    }

    /**
     * Deletes a documentation type by its ID.
     *
     * @param id The ID of the documentation type to delete.
     */
    @Operation(
            summary = "Delete a documentation type by ID",
            description = "Deletes a specific documentation type based on its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.NO_CONTENT,
                    description = ApiResponseConstants.NO_CONTENT_MESSAGE
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.NOT_FOUND,
                    description = ApiResponseConstants.NOT_FOUND_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public void deleteDocumentationTypeById(@PathVariable Long id) {
        documentationTypeService.deleteDocumentationTypeById(id);
    }
}
