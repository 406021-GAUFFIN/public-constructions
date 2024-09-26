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
import ar.edu.utn.frc.tup.lc.iv.error.WorkerCreationException;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkerServiceImpl implements WorkerService {

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




    @Override
    @Transactional
    public WorkerResponseDto createWorker(WorkerRequestDto workerRequestDto) {
        validateUserExistence(workerRequestDto);
        ConstructionEntity constructionEntity = constructionRepository.findById(workerRequestDto.getConstructionId())
                .orElseThrow(() -> new ConstructionNotFoundException(
                        "Construction with ID " + workerRequestDto.getConstructionId() + " not found.")
                );
        WorkerSpecialityEntity workerSpeciality = null;//here implement crud for worker speciality
        //and implement the same that constructionEntity

        WorkerEntity workerEntity = modelMapper.map(workerRequestDto, WorkerEntity.class);
        workerEntity.setConstruction(constructionEntity);
        workerEntity.setWorkerSpecialityType(workerSpeciality);
        workerEntity.setDocumentationWorker(new ArrayList<>());
        workerEntity.setAvailableToWork(false);

        ContactResponseDto contactResponseDto = createContact(workerRequestDto.getContact());
        workerEntity.setContactId(contactResponseDto.getId());
        workerEntity.setContactId(contactResponseDto.getId());
        workerEntity.setCreatedBy(workerEntity.getCreatedBy());

        try {
            WorkerEntity workerSaved = workerRepository.save(workerEntity);

            WorkerResponseDto response = modelMapper.map(workerSaved, WorkerResponseDto.class);
            response.setContact(modelMapper.map(contactResponseDto,ContactRequestDto.class));
            return response;
        }catch (Exception e) {
            throw new WorkerCreationException("Error when creating the user: " + e.getMessage());
        }

    }

    private void validateUserExistence(WorkerRequestDto workerRequestDto) {
        checkCuilAndDocumentNotNull(workerRequestDto);

        validateExistingCuil(workerRequestDto.getCuil());

        validateExistingDocument(workerRequestDto.getDocument());

        if (workerRequestDto.getDocument() != null) {
            validateCuilContainsDocument(workerRequestDto.getDocument());
        }

        if (workerRequestDto.getCuil() != null) {
            validateDocumentContainsCuil(workerRequestDto.getCuil());
        }
    }

    private void checkCuilAndDocumentNotNull(WorkerRequestDto workerRequestDto) {
        if (workerRequestDto.getDocument() == null && workerRequestDto.getCuil() == null) {
            throw new IllegalArgumentException("Either CUIL or Document Number must be provided.");
        }
    }

    private void validateExistingCuil(String cuil) {
        Optional<WorkerEntity> worker = workerRepository.findWorkerEntityByCuil(cuil);
        if (cuil != null && worker.isPresent()) {
            throw new WorkerAlreadyExistsException("A user with this CUIL already exists.");
        }
    }

    private void validateExistingDocument(String document) {
        Optional<WorkerEntity> worker = workerRepository.findWorkerEntityByDocument(document);
        if (document != null && worker.isPresent()) {
            throw new WorkerAlreadyExistsException("A user with this document already exists.");
        }
    }

    private void validateCuilContainsDocument(String document) {
        List<WorkerEntity> workersWithMatchingCuil = workerRepository.findWorkerEntityByCuilContaining(document);
        if (!workersWithMatchingCuil.isEmpty()) {
            throw new WorkerAlreadyExistsException("A CUIL exists that contains the provided document number.");
        }
    }

    private void validateDocumentContainsCuil(String cuil) {
        String documentInsideCuil = cuil.substring(2, cuil.length() - 1);
        List<WorkerEntity> workersWithMatchingDocument = workerRepository.findWorkerEntityByDocumentContaining(documentInsideCuil);
        if (!workersWithMatchingDocument.isEmpty()) {
            throw new WorkerAlreadyExistsException("A document exists that contains the provided CUIL number.");
        }
    }


    private ContactResponseDto createContact(ContactRequestDto contactRequestDto) {
      return contactsClient.getContact(contactRequestDto);
    }
}
