package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.documentation.DocumentationTypeRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.DocumentationType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for handling operations related to documentation types.
 */
@Service
public interface DocumentationTypeService {

    /**
     * Retrieves a list of all documentation types.
     *
     * @return A list of DocumentationType representing
     * all available documentation types.
     */
    List<DocumentationType> getDocumentationTypeList();

    /**
     * Retrieves a specific documentation type by its ID.
     *
     * @param id The ID of the documentation type to retrieve.
     * @return A DocumentationType representing the documentation type
     * with the specified ID.
     */
    DocumentationType getDocumentationTypeById(Long id);

    /**
     * Creates a new documentation type.
     *
     * @param documentationTypeRequestDto The DTO containing the details of the
     *                                    documentation type to be created.
     * @return A DocumentationType representing the newly created documentation type.
     */
    DocumentationType createDocumentationType(DocumentationTypeRequestDto documentationTypeRequestDto);

    /**
     * Updates an existing documentation type.
     *
     * @param id                          The ID of the documentation type to update.
     * @param documentationTypeRequestDto The DTO containing the updated details
     *                                    of the documentation type.
     * @return A DocumentationType representing the updated documentation type.
     */
    DocumentationType updateDocumentationType(Long id, DocumentationTypeRequestDto documentationTypeRequestDto);

    /**
     * Deletes a documentation type by its ID.
     *
     * @param id The ID of the documentation type to delete.
     */
    void deleteDocumentationTypeById(Long id);
}
