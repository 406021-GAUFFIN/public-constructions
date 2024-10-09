package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerSpecialityRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.worker.WorkerSpeciality;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for handling operations related to workerSpecialities.
 */
@Service
public interface WorkerSpecialityService {

    /**
     * Retrieves a list of all workerSpecialities.
     *
     * @return A list of WorkerSpeciality representing
     * all available workerSpecialities.
     */
    List<WorkerSpeciality> getWorkerSpecialityList();

    /**
     * Retrieves a specific workerSpeciality by its ID.
     *
     * @param id The ID of the worker speciality to retrieve.
     * @return A WorkerSpeciality representing the worker speciality
     * with the specified ID.
     */
    WorkerSpeciality getWorkerSpecialityById(Long id);

    /**
     * Creates a new workerSpeciality.
     *
     * @param workerSpecialityRequestDto The DTO containing the details of the
     *                       workerSpeciality to be created.
     * @return A WorkerSpeciality representing the newly created workerSpeciality.
     */
    WorkerSpeciality createWorkerSpeciality(WorkerSpecialityRequestDto workerSpecialityRequestDto);

    /**
     * Updates an existing workerSpeciality.
     *
     * @param id             The ID of the worker speciality to update.
     * @param workerSpecialityRequestDto The DTO containing the updated details
     *                       of the worker speciality.
     * @return A WorkerSpeciality representing the updated workerSpeciality.
     */
    WorkerSpeciality updateWorkerSpeciality(Long id, WorkerSpecialityRequestDto workerSpecialityRequestDto);

    /**
     * Deletes a workerSpeciality by its ID.
     *
     * @param id The ID of the worker speciality to delete.
     */
    void deleteWorkerSpecialityById(Long id);
}
