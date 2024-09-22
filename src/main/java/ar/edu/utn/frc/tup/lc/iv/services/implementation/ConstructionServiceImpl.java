package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConstructionServiceImpl implements ConstructionService {
    private final ConstructionRepository constructionRepository;

    private final ModelMapper modelMapper;

    @Override
    public ConstructionResponseDto registerConstruction(ConstructionRequestDto constructionRequest) {
        Optional<ConstructionEntity> constructionEntityFound = constructionRepository.findByPlotId(constructionRequest.getPlotId());

        if (constructionEntityFound.isPresent()) {
            throw new IllegalArgumentException("There is already a construction for the lot: " + constructionRequest.getPlotId());
        }

        ConstructionEntity constructionToSave = modelMapper.map(constructionRequest, ConstructionEntity.class);
        constructionToSave.setApprovedByMunicipality(false);
        constructionToSave.setConstructionStatus(ConstructionStatus.IN_PROGRESS);

        ConstructionEntity savedConstruction = constructionRepository.save(constructionToSave);

        return modelMapper.map(savedConstruction, ConstructionResponseDto.class);
    }
}
