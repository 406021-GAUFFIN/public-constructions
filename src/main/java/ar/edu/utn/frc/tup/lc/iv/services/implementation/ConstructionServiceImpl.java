package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionAlreadyExistsException;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.error.PlotNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionSpecification;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final CadastreClient cadastreClient;


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
        if (!cadastreClient.plotExists(constructionRequest.getPlotId())) {
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
     * if the update fails due to invalid status or ID.
     */
    @Override
    @Transactional
    public ConstructionResponseDto updateConstructionStatus(ConstructionUpdateStatusRequestDto updateStatusRequestDto) {
        ConstructionEntity constructionEntity = constructionRepository.findById(updateStatusRequestDto.getConstructionId())
                .orElseThrow(() -> new ConstructionNotFoundException(
                        "Construction with ID " + updateStatusRequestDto.getConstructionId() + " not found.")
                );

        ConstructionStatus newStatus = updateStatusRequestDto.getStatus();

        newStatus.validateTransition(constructionEntity);

        constructionEntity.setConstructionStatus(newStatus);
        newStatus.handleStateTransition(constructionEntity);

        ConstructionEntity constructionSaved = constructionRepository.save(constructionEntity);
        return modelMapper.map(constructionSaved, ConstructionResponseDto.class);
    }

    /**
     * Retrieves a list of all constructions.
     *
     * @return A list of construction response DTOs.
     */
    @Override
    public List<ConstructionResponseDto> getAllConstructions() {
        List<ConstructionEntity> constructions = constructionRepository.findAll();

        return constructions.stream()
                .map(construction -> modelMapper.map(construction, ConstructionResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a construction by its ID.
     *
     * @param id The ID of the construction to retrieve.
     * @return The response DTO of the retrieved construction.
     */
    @Override
    public ConstructionResponseDto getConstructionById(Long id) {
        ConstructionEntity construction = constructionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Construction not found with ID: " + id));


        return modelMapper.map(construction, ConstructionResponseDto.class);
    }

    /**
     * Retrieves a paginated list of constructions filtered by status.
     *
     * @param pageable           The pagination information.
     * @param constructionStatus The list of statuses to filter by.
     * @return A page of construction request DTOs.
     */
    @Override
    public Page<ConstructionResponseDto> getAllConstructionsPageable(Pageable pageable, List<ConstructionStatus> constructionStatus) {
        Specification<ConstructionEntity> spec = ConstructionSpecification.inStatus(constructionStatus);

        Page<ConstructionEntity> constructionEntityPage = constructionRepository.findAll(spec, pageable);

        return constructionEntityPage.map(constructionEntity -> modelMapper.map(constructionEntity, ConstructionResponseDto.class));
    }
}



