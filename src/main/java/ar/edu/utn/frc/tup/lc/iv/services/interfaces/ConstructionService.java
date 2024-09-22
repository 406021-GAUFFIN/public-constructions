package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface ConstructionService {
    ConstructionResponseDto registerConstruction(ConstructionRequestDto constructionRequest);
}
