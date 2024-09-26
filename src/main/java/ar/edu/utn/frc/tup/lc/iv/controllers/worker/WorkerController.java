package ar.edu.utn.frc.tup.lc.iv.controllers.worker;

import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

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
}
