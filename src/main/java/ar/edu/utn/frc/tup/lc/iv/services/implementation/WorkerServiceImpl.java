package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.clients.ContactsClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.ContactRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.ContactResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerSpecialityEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.error.WorkerAlreadyExistsException;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the WorkerService interface.
 */
@RequiredArgsConstructor
@Service
public class  WorkerServiceImpl implements WorkerService {

    /**
     * Repository for accessing construction entities.
     */
    private final ConstructionRepository constructionRepository;

    /**
     * Repository for accessing worker entities.
     */
    private final WorkerRepository workerRepository;

    /**
     * Model mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Client for interacting with the Plot microservice.
     */
    private final ContactsClient contactsClient;



    /**
     * Creates a new worker and saves it to the database.
     * @param workerRequestDto the worker data
     * @return the created worker's response DTO
     */
    @Override
    @Transactional
    public WorkerResponseDto createWorker(WorkerRequestDto workerRequestDto) {
        validateUserExistence(workerRequestDto);
        ConstructionEntity constructionEntity = constructionRepository.findById(workerRequestDto.getConstructionId())
                .orElseThrow(() -> new ConstructionNotFoundException(
                        "Construction with ID " + workerRequestDto.getConstructionId() + " not found.")
                );
        WorkerSpecialityEntity workerSpeciality = null; //here implement crud for worker speciality
        //and implement the same that constructionEntity

        WorkerEntity newWorker = modelMapper.map(workerRequestDto, WorkerEntity.class);
        newWorker.setId(null);

        newWorker.setConstruction(constructionEntity);
        newWorker.setWorkerSpecialityType(workerSpeciality);
        newWorker.setDocumentationWorker(new ArrayList<>());
        newWorker.setAvailableToWork(false);

        ContactResponseDto contactResponseDto = createContact(workerRequestDto.getContact());
        newWorker.setContactId(contactResponseDto.getId());
        newWorker.setContactId(contactResponseDto.getId());
        newWorker.setCreatedBy(newWorker.getCreatedBy());

        WorkerEntity workerSaved = workerRepository.save(newWorker);

        WorkerResponseDto response = modelMapper.map(workerSaved, WorkerResponseDto.class);
        response.setContact(modelMapper.map(contactResponseDto, ContactRequestDto.class));
        return response;



    }



    /**
     * Validates the existence of a worker by CUIL or Document.
     * @param workerRequestDto the worker request data
     */
    private void validateUserExistence(WorkerRequestDto workerRequestDto) {
        checkCuilAndDocumentNotNull(workerRequestDto);

        if (workerRequestDto.getCuil() != null) {
            validateExistingCuil(workerRequestDto.getCuil());
        }

        if (workerRequestDto.getDocument() != null) {
            validateExistingDocument(workerRequestDto.getDocument());
        }

        if (workerRequestDto.getDocument() != null) {
            validateCuilContainsDocument(workerRequestDto.getDocument());
        }

        if (workerRequestDto.getCuil() != null) {
            validateDocumentContainsCuil(workerRequestDto.getCuil());
        }
    }
    /**
     * Ensures that either CUIL or Document is provided.
     * @param workerRequestDto the worker request data
     */
    private void checkCuilAndDocumentNotNull(WorkerRequestDto workerRequestDto) {
        if (workerRequestDto.getDocument() == null && workerRequestDto.getCuil() == null) {
            throw new IllegalArgumentException("Either CUIL or Document Number must be provided.");
        }
    }
    /**
     * Validates if a worker with the given CUIL exists.
     * @param cuil the CUIL to check
     */
    private void validateExistingCuil(String cuil) {
        Optional<WorkerEntity> worker = workerRepository.findWorkerEntityByCuil(cuil);
        if (cuil != null && worker.isPresent()) {
            throw new WorkerAlreadyExistsException("A user with this CUIL already exists.");
        }
    }

    /**
     * Validates if a worker with the given Document exists.
     * @param document the document to check
     */
    private void validateExistingDocument(String document) {
        Optional<WorkerEntity> worker = workerRepository.findWorkerEntityByDocument(document);
        if (document != null && worker.isPresent()) {
            throw new WorkerAlreadyExistsException("A user with this document already exists.");
        }
    }

    /**
     * Checks if any CUIL contains the given document.
     * @param document the document to check
     */
    private void validateCuilContainsDocument(String document) {
        List<WorkerEntity> workersWithMatchingCuil = workerRepository.findWorkerEntityByCuilContaining(document);
        if (!workersWithMatchingCuil.isEmpty()) {
            throw new WorkerAlreadyExistsException("A CUIL exists that contains the provided document number.");
        }
    }

    /**
     * Checks if any document is contained within the given CUIL.
     * @param cuil the CUIL to check
     */
    private void validateDocumentContainsCuil(String cuil) {
        String documentInsideCuil = cuil.substring(2, cuil.length() - 1);
        validateCuilContainsDocument(documentInsideCuil);
    }

    /**
     * Creates a contact using the contact client.
     * @param contactRequestDto the contact request data
     * @return the created contact's response DTO
     */
    private ContactResponseDto createContact(ContactRequestDto contactRequestDto) {
        return contactsClient.getContact(contactRequestDto);
    }

}
