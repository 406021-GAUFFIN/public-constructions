package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.documentation.DocumentationTypeRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.error.DocumentationTypeNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.DocumentationType;
import ar.edu.utn.frc.tup.lc.iv.repositories.DocumentationTypeRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.DocumentationTypeService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the DocumentationTypeService interface.
 * Provides methods for managing documentation types.
 */
@RequiredArgsConstructor
@Service
public class DocumentationTypeServiceImpl implements DocumentationTypeService {

    /**
     * Repository for accessing documentation type entities.
     */
    private final DocumentationTypeRepository documentationTypeRepository;

    /**
     * Model mapper for converting between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Retrieves a list of all documentation types.
     *
     * @return A list of documentation type.
     */
    @Override
    public List<DocumentationType> getDocumentationTypeList() {
        return documentationTypeRepository.findAll().stream()
                .map(documentationTypeEntity -> modelMapper.map(documentationTypeEntity, DocumentationType.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a documentation type by its ID.
     *
     * @param id The ID of the documentation type to retrieve.
     * @return The documentation type.
     * @throws DocumentationTypeNotFoundException if documentation is not found.
     */
    @Override
    public DocumentationType getDocumentationTypeById(Long id) {
        DocumentationTypeEntity documentationTypeEntity = documentationTypeRepository.findById(id)
                .orElseThrow(() -> new DocumentationTypeNotFoundException("Documentation Type with ID " + id + " not found"));
        return modelMapper.map(documentationTypeEntity, DocumentationType.class);
    }

    /**
     * Creates a new documentation type based on the provided request DTO.
     *
     * @param documentationTypeRequestDto The DTO containing
     *                                    documentation type details.
     * @return The created documentation type.
     */
    @Override
    public DocumentationType createDocumentationType(DocumentationTypeRequestDto documentationTypeRequestDto) {
        DocumentationTypeEntity documentationTypeEntity = modelMapper.map(documentationTypeRequestDto, DocumentationTypeEntity.class);
        DocumentationTypeEntity documentationTypeEntitySaved = documentationTypeRepository.save(documentationTypeEntity);

        return modelMapper.map(documentationTypeEntitySaved, DocumentationType.class);
    }

    /**
     * Updates an existing documentation type with the provided details.
     * @param id                          The ID of the documentation type to update.
     * @param documentationTypeRequestDto The DTO containing the
     *                                    updated documentation details.
     * @return The updated documentation type.
     * @throws DocumentationTypeNotFoundException if the documentation is not found.
     */
    @Override
    public DocumentationType updateDocumentationType(Long id, DocumentationTypeRequestDto documentationTypeRequestDto) {
        DocumentationTypeEntity documentationTypeEntity = documentationTypeRepository.findById(id)
                .orElseThrow(() -> new DocumentationTypeNotFoundException("Documentation Type with ID " + id + " not found"));

        modelMapper.map(documentationTypeRequestDto, documentationTypeEntity);

        DocumentationTypeEntity documentationTypeEntitySaved = documentationTypeRepository.save(documentationTypeEntity);

        return modelMapper.map(documentationTypeEntitySaved, DocumentationType.class);
    }

    /**
     * Deletes a documentation type by its ID.
     *
     * @param id The ID of the documentation type to delete.
     * @throws DocumentationTypeNotFoundException if the
     *                                            documentation type is not found.
     */
    @Override
    public void deleteDocumentationTypeById(Long id) {
        documentationTypeRepository.findById(id)
                .orElseThrow(() -> new DocumentationTypeNotFoundException("Documentation Type with ID " + id + " not found"));

        documentationTypeRepository.deleteById(id);
    }
}
