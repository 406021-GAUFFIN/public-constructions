package ar.edu.utn.frc.tup.lc.iv.controllers.worker;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


/**
 * Controller for managing workers.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/workers")
@CrossOrigin(origins = "*")
public class WorkerController {

    /**
     * Service for handling worker logic.
     */
    private final WorkerService workerService;

    /**
     * Creates a new worker based on the provided request DTO.
     *
     * @param request Data transfer object containing worker details.
     * @return Response DTO of the created worker.
     */
    @Operation(
            summary = "Create a new worker",
            description = "Creates a new worker by providing the necessary details, such as CUIL, document, and contact information."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Worker created successfully",
                    content = @Content(
                            schema = @Schema(implementation = WorkerResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid worker request",
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
    public ResponseEntity<WorkerResponseDto> addWorker(@RequestBody WorkerRequestDto request) {
        if (request.getCuil().isEmpty()) {
            request.setCuil(null);
        } else if (request.getDocument().isEmpty()) {
            request.setDocument(null);
        }
        return ResponseEntity.ok(workerService.createWorker(request));
    }

    /**
     * Adds documentation for a worker based on the provided request DTO.
     *
     * @param request Data transfer object containing documentation details.
     * @return Response DTO of the added worker documentation.
     */
    @Operation(
            summary = "Add documentation for a worker",
            description = "Adds documentation for an existing worker, including details like certifications and qualifications."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Documentation added successfully",
                    content = @Content(
                            schema = @Schema(implementation = WorkerDocumentationResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid documentation request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Worker not found",
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
    @PostMapping("/documentation")
    public ResponseEntity<WorkerDocumentationResponseDto> addDocumentation(@RequestBody WorkerDocumentationRequestDto request) {
        return ResponseEntity.ok(workerService.addWorkerDocumentation(request));
    }

    /**
     * Allows access to a worker based on the provided worker ID and comment.
     *
     * @param comment   Comment regarding the access permission.
     * @param workerId  ID of the worker to whom access is being granted.
     * @return Response DTO containing authorization range details.
     */
    @Operation(
            summary = "Allow access to a worker",
            description = "Grants access to a worker with a specified comment."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Access granted successfully",
                    content = @Content(
                            schema = @Schema(implementation = AuthorizationRangeResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Worker not found",
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
    @PostMapping("/allow-access/{workerId}")
    public ResponseEntity<AuthorizationRangeResponseDto> allowWorkerAccess(@RequestParam String comment, @PathVariable Long workerId) {
        return ResponseEntity.ok(workerService.allowWorkerAccess(workerId, comment));
    }

    /**
     * Retrieves a list of all ´workers.
     *
     * @return A list of all worker response DTOs.
     */
    @GetMapping("/get")
    public ResponseEntity<List<WorkerResponseDto>> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAllWorkers());
    }

    /**
     * Retrieves a pageable list of workers.
     *
     * @param page The page number to retrieve.
     * @param size The number of items per page.
     * @return A pageable list of worker WorkerResponseDTOs.
     */
    @GetMapping("/get/paged")
    public ResponseEntity<Page<WorkerResponseDto>> getAllWorkersPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(workerService.getAllWorkersPage(pageable), HttpStatus.OK);
    }

    /**
     * Retrieves a construction by its ID.
     *
     * @param id The ID of the construction to retrieve.
     * @return The response it´s a List DTO of the retrieved construction.
     */
    @GetMapping("/get/workers/construction/{id}")
    public ResponseEntity<List<WorkerResponseDto>> getWorkerConstruction(@PathVariable Long id) {
        return ResponseEntity.ok((workerService.getAllWorkersOfConstruction(id)));
    }

    /**
     * Endpoint to unassign a worker from a construction.
     *
     * @param workerId the ID of the worker to unassign.
     * @return Response entity with a success message.
     */
    @PutMapping("/{workerId}/unassign")
    public ResponseEntity<String> unassignWorkerFromConstruction(@PathVariable Long workerId) {
        workerService.unassignWorkerFromConstruction(workerId);
        return ResponseEntity.ok("Worker unassigned from construction successfully");
    }

    /**
     * Endpoint to assign a worker to a construction.
     *
     * @param workerId the ID of the worker.
     * @param constructionId the ID of the construction.
     * @return Response entity with a success message.
     */
    @PutMapping("/{workerId}/assign/{constructionId}")
    public ResponseEntity<String> assignWorkerToConstruction(@PathVariable Long workerId,
                                                             @PathVariable Long constructionId) {
        workerService.assignWorkerToConstruction(workerId, constructionId);
        return ResponseEntity.ok("Worker assigned to construction successfully");
    }

    /**
     * Endpoint to delete a worker from the system.
     *
     * @param workerId the ID of the worker to delete.
     * @return Response entity with a success message.
     */
    @DeleteMapping("/{workerId}")
    public ResponseEntity<String> deleteWorker(@PathVariable Long workerId) {
        workerService.deleteWorker(workerId);
        return ResponseEntity.ok("Worker deleted successfully");
    }
}
