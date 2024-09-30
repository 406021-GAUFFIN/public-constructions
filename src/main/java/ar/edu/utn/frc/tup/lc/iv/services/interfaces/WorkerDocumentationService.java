package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import org.springframework.stereotype.Service;

@Service
public interface WorkerDocumentationService {

    WorkerDocumentationEntity createWorkerDocumentation(WorkerDocumentationRequestDto requestDto);
    WorkerDocumentationEntity getLatestWorkerDocumentation(Long workerId);
}
