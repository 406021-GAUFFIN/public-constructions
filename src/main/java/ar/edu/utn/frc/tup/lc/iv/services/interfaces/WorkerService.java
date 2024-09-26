package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.stereotype.Service;

@Service
public interface WorkerService {

    WorkerResponseDto createWorker(WorkerRequestDto workerRequestDto);
}
