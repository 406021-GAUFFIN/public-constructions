package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerSpecialityRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerSpecialityEntity;
import ar.edu.utn.frc.tup.lc.iv.error.WorkerSpecialityNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.worker.WorkerSpeciality;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerSpecialityRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerSpecialityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the WorkerSpecialityService interface.
 * Provides methods for managing workerSpecialities.
 */
@RequiredArgsConstructor
@Service
public class WorkerSpecialityServiceImpl implements WorkerSpecialityService {
    /**
     * Repository for accessing workerSpeciality entities.
     */
    private final WorkerSpecialityRepository workerSpecialityRepository;

    /**
     * ModelMapper instance for converting between between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Retrieves a list of all workerSpecialities.
     *
     * @return A list of workerSpecialities.
     */
    @Override
    public List<WorkerSpeciality> getWorkerSpecialityList() {
        return workerSpecialityRepository.findAll().stream()
                .map(workerSpecialityEntity -> modelMapper.map(workerSpecialityEntity, WorkerSpeciality.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a workerSpeciality by its ID.
     *
     * @param id The ID of the worker speciality to retrieve.
     * @return The workerSpeciality.
     * @throws WorkerSpecialityNotFoundException if workerSpeciality is not found.
     */
    @Override
    public WorkerSpeciality getWorkerSpecialityById(Long id) {
        WorkerSpecialityEntity workerSpecialityEntity = workerSpecialityRepository.findById(id)
                .orElseThrow(() -> new WorkerSpecialityNotFoundException("WorkerSpeciality with ID " + id + " not found"));
        return modelMapper.map(workerSpecialityEntity, WorkerSpeciality.class);
    }

    /**
     * Creates a new workerSpeciality based on the provided request DTO.
     *
     * @param workerSpecialityRequestDto The DTO containing workerSpeciality details.
     * @return The created workerSpeciality.
     */
    @Override
    public WorkerSpeciality createWorkerSpeciality(WorkerSpecialityRequestDto workerSpecialityRequestDto) {
        WorkerSpecialityEntity workerSpecialityEntity = modelMapper.map(workerSpecialityRequestDto, WorkerSpecialityEntity.class);
        WorkerSpecialityEntity workerSpecialityEntitySaved = workerSpecialityRepository.save(workerSpecialityEntity);

        return modelMapper.map(workerSpecialityEntitySaved, WorkerSpeciality.class);
    }

    /**
     * Updates an existing workerSpeciality with the provided details.
     * @param id                         The ID of the documentation type to update.
     * @param workerSpecialityRequestDto The DTO containing the updated
     *                                   workerSpeciality details.
     * @return The updated workerSpeciality.
     * @throws WorkerSpecialityNotFoundException workerSpeciality is not found.
     */
    @Override
    public WorkerSpeciality updateWorkerSpeciality(Long id, WorkerSpecialityRequestDto workerSpecialityRequestDto) {
        WorkerSpecialityEntity workerSpecialityEntity = workerSpecialityRepository.findById(id)
                .orElseThrow(() -> new WorkerSpecialityNotFoundException("WorkerSpeciality with ID " + id + " not found"));

        modelMapper.map(workerSpecialityRequestDto, workerSpecialityEntity);

        WorkerSpecialityEntity workerSpecialityEntitySaved = workerSpecialityRepository.save(workerSpecialityEntity);

        return modelMapper.map(workerSpecialityEntitySaved, WorkerSpeciality.class);
    }

    /**
     * Deletes a workerSpeciality by its ID.
     *
     * @param id The ID of the worker speciality to delete.
     * @throws WorkerSpecialityNotFoundException if the
     *                                           workerSpeciality is not found.
     */
    @Override
    public void deleteWorkerSpecialityById(Long id) {
        workerSpecialityRepository.findById(id)
                .orElseThrow(() -> new WorkerSpecialityNotFoundException("WorkerSpeciality with ID " + id + " not found"));

        workerSpecialityRepository.deleteById(id);
    }
}
