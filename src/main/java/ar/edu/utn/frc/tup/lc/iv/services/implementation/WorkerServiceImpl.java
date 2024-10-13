package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.clients.AccessesClient;
import ar.edu.utn.frc.tup.lc.iv.clients.ContactsClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.AuthorizationRangeResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.accesses.RegisterAuthorizationRangesDTO;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.external.contacts.ContactResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.worker.documentation.WorkerDocumentationResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.WorkerDocumentationEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerEntity;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerSpecialityEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.error.WorkerAlreadyExistsException;
import ar.edu.utn.frc.tup.lc.iv.error.WorkerNotAvailableException;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerDocumentationService;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
     * Client for interacting with the Accesses microservice.
     */
    private final AccessesClient accessesClient;

    /**
     * Service for interacting with the documentation service.
     */
    private final WorkerDocumentationService workerDocumentationService;

    /**
     * The hour at which work starts for visitors.
     * This constant represents the start time of the
     * workday in 24-hour format.
     */
    private static final int WORK_START_HOUR = 8;

    /**
     * The hour at which work ends for visitors.
     * This constant represents the end time of
     * the workday in 24-hour format.
     */
    private static final int WORK_END_HOUR = 18;

    /**
     * Creates a new worker and saves it to the database.
     *
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
     * Adds documentation for a worker and updates their availability status.
     *
     * @param workerDocumentationRequestDto DTO containing worker
     *                                      documentation details.
     * @return WorkerDocumentationResponseDto containing created worker.
     * @throws EntityNotFoundException if the worker is not found.
     */
    @Override
    @Transactional
    public WorkerDocumentationResponseDto addWorkerDocumentation(WorkerDocumentationRequestDto workerDocumentationRequestDto) {
        Optional<WorkerEntity> workerEntity = workerRepository.findById(workerDocumentationRequestDto.getWorkerId());

        if (workerEntity.isEmpty()) {
            throw new EntityNotFoundException("Worker with id " + workerDocumentationRequestDto.getWorkerId() + " not found");
        }


        WorkerEntity worker = workerEntity.get();
        WorkerDocumentationEntity documentationToAdd = workerDocumentationService.createWorkerDocumentation(workerDocumentationRequestDto);
        worker.getDocumentationWorker().add(documentationToAdd);
        worker.setAvailableToWork(true);
        WorkerEntity savedWorker = workerRepository.save(worker);
        String workerFullName = savedWorker.getName() + " " + savedWorker.getLastName();

        WorkerDocumentationResponseDto response = modelMapper.map(documentationToAdd, WorkerDocumentationResponseDto.class);
        response.setWorkerFullName(workerFullName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        response.setExpireDate(documentationToAdd.getExpireDate().format(formatter));
        response.setWorkerId(savedWorker.getId());

        return response;

    }


    /**
     * Validates the existence of a worker by CUIL or Document.
     *
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
     *
     * @param workerRequestDto the worker request data
     */
    private void checkCuilAndDocumentNotNull(WorkerRequestDto workerRequestDto) {
        if (workerRequestDto.getDocument() == null && workerRequestDto.getCuil() == null) {
            throw new IllegalArgumentException("Either CUIL or Document Number must be provided.");
        }
    }

    /**
     * Validates if a worker with the given CUIL exists.
     *
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
     *
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
     *
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
     *
     * @param cuil the CUIL to check
     */
    private void validateDocumentContainsCuil(String cuil) {
        String documentInsideCuil = cuil.substring(2, cuil.length() - 1);
        validateCuilContainsDocument(documentInsideCuil);
    }

    /**
     * Creates a contact using the contact client.
     *
     * @param contactRequestDto the contact request data
     * @return the created contact's response DTO
     */
    private ContactResponseDto createContact(ContactRequestDto contactRequestDto) {
        return contactsClient.getContact(contactRequestDto);
    }

    /**
     * Allows access for a worker by creating an authorization request.
     *
     * @param workerId ID of the worker requesting access.
     * @param comment  Comment associated with the authorization request.
     * @return AuthorizationRangeResponseDto containing the client response.
     * @throws EntityNotFoundException     if the worker is not found.
     * @throws WorkerNotAvailableException if the worker is not available to work.
     */
    @Override
    public AuthorizationRangeResponseDto allowWorkerAccess(Long workerId, String comment) {

        RegisterAuthorizationRangesDTO request = new RegisterAuthorizationRangesDTO();

        WorkerDocumentationEntity documentation = workerDocumentationService.getLatestWorkerDocumentation(workerId);
        Optional<WorkerEntity> workerEntity = workerRepository.findById(workerId);
        if (workerEntity.isEmpty()) {
            throw new EntityNotFoundException("Worker with id " + workerId + " not found");
        }
        if (!workerEntity.get().getAvailableToWork()) {
            throw new WorkerNotAvailableException("Worker with id " + workerId + " is not available to work");
        }

        request.setDateTo(documentation.getExpireDate());

        if (workerEntity.get().getDocument() != null) {
            request.setExternalId(Long.valueOf(workerEntity.get().getDocument()));
        } else {
            request.setExternalId(Long.valueOf(workerEntity.get().getCuil()));
        }

        request.setAuthTypeId(1L);
        request.setPlotId(workerEntity.get().getConstruction().getPlotId());
        request.setComment(comment);
        request.setDateFrom(LocalDate.now());

        List<DayOfWeek> daysOfWeek = Arrays.asList(
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY
        );
        request.setVisitorId(1L); //this is hardcoded here. Here do a get of visitors and create the visitor
        request.setDayOfWeeks(daysOfWeek);
        request.setHourFrom(LocalTime.of(WORK_START_HOUR, 0));
        request.setHourTo(LocalTime.of(WORK_END_HOUR, 0));

        return accessesClient.allowAccess(request);


    }

    /**
     * Retrieves all workers.
     *
     * @return A list of workers response DTOs.
     */
    @Override
    public List<WorkerResponseDto> getAllWorkers() {
        List<WorkerEntity> workers = workerRepository.findAll();

        return workers.stream()
                .map(worker -> modelMapper.map(worker, WorkerResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a paginated list of workers.
     *
     * @param pageable Pagination information.
     * @return A pageable list of worker reqponse DTOs.
     */
    @Override
    public Page<WorkerResponseDto> getAllWorkersPage(Pageable pageable) {

        Page<WorkerEntity> workerEntityPage = workerRepository.findAll(pageable);

        return workerEntityPage.map(workerEntity -> modelMapper.map(workerEntity, WorkerResponseDto.class));
    }

    /**
     * Retrieves all workers of contruction.
     *
     * @return A list of workers response DTOs.
     */
    @Override
    public List<WorkerResponseDto> getAllWorkersOfConstruction(Long constructionId) {
        List<WorkerEntity> workers = workerRepository.findByConstructionId(constructionId);

        if (workers.isEmpty()) {
            throw new EntityNotFoundException("Construction not found with ID: " + constructionId);
        }

        return workers.stream()
                .map(worker -> modelMapper.map(worker, WorkerResponseDto.class))
                .collect(Collectors.toList());
    }

}
