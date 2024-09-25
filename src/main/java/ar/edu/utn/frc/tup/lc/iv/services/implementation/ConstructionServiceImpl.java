package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.clients.PlotClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionAlreadyExistsException;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.error.PlotNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * Implementation of the ConstructionService interface.
 */
@RequiredArgsConstructor
@Service
public class ConstructionServiceImpl implements ConstructionService {

    /**
     * Repository for accessing construction entities.
     */
    private final ConstructionRepository constructionRepository;

    /**
     * Model mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Client for interacting with the Plot microservice.
     */
    private final PlotClient plotClient;


    /**
     * Initializes the model mapper configuration.
     */
    @PostConstruct
    public void init() {
        modelMapper.addMappings(new PropertyMap<ConstructionRequestDto, ConstructionEntity>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    /**
     * Registers a new construction based on the provided request DTO.
     *
     * @param constructionRequest The data transfer object
     *                            containing construction details.
     * @return The response DTO of the registered construction.
     */
    @Override
    @Transactional
    public ConstructionResponseDto registerConstruction(ConstructionRequestDto constructionRequest) {
        if (!plotClient.plotExists(constructionRequest.getPlotId())) {
            throw new PlotNotFoundException("The plot with ID " + constructionRequest.getPlotId() + " does not exist.");
        }

        Optional<ConstructionEntity> constructionEntityFound = constructionRepository.findByPlotId(constructionRequest.getPlotId());

        if (constructionEntityFound.isPresent()) {
            throw new ConstructionAlreadyExistsException(
                    "There is already a construction for the plot: " + constructionRequest.getPlotId()
            );
        }

        ConstructionEntity constructionToSave = modelMapper.map(constructionRequest, ConstructionEntity.class);

        constructionToSave.setApprovedByMunicipality(false);
        constructionToSave.setConstructionStatus(ConstructionStatus.PLANNED);

        ConstructionEntity savedConstruction = constructionRepository.save(constructionToSave);

        return modelMapper.map(savedConstruction, ConstructionResponseDto.class);
    }


    /**
     * Updates the status of an existing construction using the provided DTO.
     *
     * @param updateStatusRequestDto DTO with construction ID and new status.
     * @return Response DTO with the result of the update.
     * @throws UpdateConstructionStatusException
     * if the update fails due to invalid status or ID.
     */
    @Override
    @Transactional
    public ConstructionUpdateStatusResponseDto updateConstructionStatus(ConstructionUpdateStatusRequestDto updateStatusRequestDto) {
        ConstructionUpdateStatusResponseDto response = new ConstructionUpdateStatusResponseDto();

        ConstructionEntity constructionEntity = constructionRepository.findById(updateStatusRequestDto.getConstructionId())
                .orElseThrow(() -> new ConstructionNotFoundException(
                        "Construction with ID " + updateStatusRequestDto.getConstructionId() + " not found.")
                );

        ConstructionStatus newStatus = updateStatusRequestDto.getStatus();

        newStatus.validateTransition(constructionEntity);

        constructionEntity.setConstructionStatus(newStatus);
        newStatus.handleStateTransition(constructionEntity);

        constructionRepository.save(constructionEntity);

        response.setMessage("Construction updated with ID " + updateStatusRequestDto.getConstructionId());
        return response;


    }
}
