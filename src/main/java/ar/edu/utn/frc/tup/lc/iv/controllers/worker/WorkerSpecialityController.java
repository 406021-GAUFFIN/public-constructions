package ar.edu.utn.frc.tup.lc.iv.controllers.worker;

import ar.edu.utn.frc.tup.lc.iv.controllers.api_response.ApiResponseConstants;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerSpecialityRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.worker.WorkerSpeciality;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerSpecialityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * Controller for managing worker specialties operations.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/workerSpecialities")
public class WorkerSpecialityController {
    /**
     * Service for handling workerSpecialities logic.
     */
    private final WorkerSpecialityService workerSpecialityService;

    /**
     * Retrieves a list of all workerSpecialities.
     *
     * @return A list of workerSpecialities.
     */
    @Operation(
            summary = "Get all workerSpecialities",
            description = "Retrieves a list of all workerSpecialities available"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = WorkerSpeciality.class)
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
    @GetMapping("/list")
    public ResponseEntity<List<WorkerSpeciality>> getWorkerSpecialityList() {
        return ResponseEntity.ok(workerSpecialityService.getWorkerSpecialityList());
    }

    /**
     * Retrieves a workerSpeciality by its ID.
     *
     * @param id The ID of the worker speciality to retrieve.
     * @return The workerSpeciality.
     */
    @Operation(
            summary = "Get a workerSpeciality by ID",
            description = "Retrieves a specific workerSpeciality based on its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = WorkerSpeciality.class)
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
    @GetMapping("/{id}")
    public ResponseEntity<WorkerSpeciality> getWorkerSpecialityById(@PathVariable Long id) {
        return ResponseEntity.ok(workerSpecialityService.getWorkerSpecialityById(id));
    }

    /**
     * Creates a new workerSpeciality based on the provided request DTO.
     *
     * @param workerSpecialityRequestDto The DTO containing workerSpeciality details.
     * @return The created workerSpeciality.
     */
    @Operation(
            summary = "Create a new workerSpeciality",
            description = "Creates a new workerSpeciality by providing the necessary details"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = WorkerSpeciality.class)
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
    @PostMapping
    public ResponseEntity<WorkerSpeciality> createWorkerSpeciality(@RequestBody WorkerSpecialityRequestDto workerSpecialityRequestDto) {
        return ResponseEntity.ok(workerSpecialityService.createWorkerSpeciality(workerSpecialityRequestDto));
    }

    /**
     * Updates an existing workerSpeciality with the provided details.
     *
     * @param id       The ID of the worker speciality to update.
     * @param workerSpecialityRequestDto The DTO containing the
     *                                   updated workerSpeciality details.
     * @return The updated workerSpeciality.
     */
    @Operation(
            summary = "Update an existing workerSpeciality",
            description = "Updates an existing workerSpeciality by providing the necessary details"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = WorkerSpeciality.class)
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
    @PutMapping("/{id}")
    public ResponseEntity<WorkerSpeciality> updateWorkerSpeciality(@PathVariable Long id,
                                                                   @RequestBody WorkerSpecialityRequestDto workerSpecialityRequestDto) {
        return ResponseEntity.ok(workerSpecialityService.updateWorkerSpeciality(id, workerSpecialityRequestDto));
    }

    /**
     * Deletes a workerSpeciality by its ID.
     *
     * @param id The ID of the worker speciality to delete.
     */
    @Operation(
            summary = "Delete a workerSpeciality by ID",
            description = "Deletes a specific workerSpeciality based on its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.NO_CONTENT,
                    description = ApiResponseConstants.NO_CONTENT_MESSAGE
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
    @DeleteMapping("/{id}")
    public void deleteWorkerSpecialityById(@PathVariable Long id) {
        workerSpecialityService.deleteWorkerSpecialityById(id);
    }
}
