package ar.edu.utn.frc.tup.lc.iv.controllers.worker;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workers")
@CrossOrigin(origins = "*")
public class WorkerController {

    private final WorkerService workerService;

    @PostMapping
    public ResponseEntity<WorkerResponseDto> addWorker(@RequestBody WorkerRequestDto request) {
        if (request.getCuil().isEmpty()){
            request.setCuil(null);
        }else if (request.getDocument().isEmpty()){
            request.setDocument(null);
        }
        return ResponseEntity.ok(workerService.createWorker(request));
    }
}
