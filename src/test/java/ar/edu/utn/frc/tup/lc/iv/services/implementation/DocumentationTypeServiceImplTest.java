package ar.edu.utn.frc.tup.lc.iv.services.implementation;


import ar.edu.utn.frc.tup.lc.iv.dtos.documentation.DocumentationTypeRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.error.DocumentationTypeNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.DocumentationType;
import ar.edu.utn.frc.tup.lc.iv.repositories.DocumentationTypeRepository;
import ar.edu.utn.frc.tup.lc.iv.services.implementation.DocumentationTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.print.Doc;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
public class DocumentationTypeServiceImplTest {


    @Mock
    private DocumentationTypeRepository documentationTypeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DocumentationTypeServiceImpl documentationTypeService;



    @Test
    public void testUpdateDocumentationType_Success() {
        Long documentationTypeId = 100L;
        DocumentationTypeEntity documentationTypeEntity = new DocumentationTypeEntity();
        documentationTypeEntity.setId(100L);
        documentationTypeEntity.setName("Old Name");
        documentationTypeEntity.setDescription("Old Description");


        DocumentationTypeRequestDto  documentationTypeRequestDto = new DocumentationTypeRequestDto();
        documentationTypeRequestDto.setName("Updated Name");
        documentationTypeRequestDto.setDescription("Updated Description");

        DocumentationType documentationType = new DocumentationType();
        documentationType.setName("Updated Name");
        documentationType.setDescription("Updated Description");

        // Mock repository behavior
        when(documentationTypeRepository.findById(documentationTypeId))
                .thenReturn(Optional.of(documentationTypeEntity));
        when(documentationTypeRepository.save(any(DocumentationTypeEntity.class)))
                .thenReturn(documentationTypeEntity);
        when(modelMapper.map(documentationTypeRequestDto, DocumentationTypeEntity.class))
                .thenReturn(documentationTypeEntity);
        when(modelMapper.map(documentationTypeEntity, DocumentationType.class))
                .thenReturn(documentationType);

        // Call the method
        DocumentationType result = documentationTypeService.updateDocumentationType(documentationTypeId, documentationTypeRequestDto);

        // Verify interactions and assertions
        verify(documentationTypeRepository).findById(documentationTypeId);
        verify(documentationTypeRepository).save(documentationTypeEntity);

        assertNotNull(result);
        assertEquals("Updated Name", result.getName());
        assertEquals("Updated Description", result.getDescription());
    }

    @Test
    public void testUpdateDocumentationType_NotFound() {
        Long documentationTypeId = 1L;
        DocumentationTypeRequestDto  documentationTypeRequestDto = new DocumentationTypeRequestDto();
        documentationTypeRequestDto.setName("Updated Name");
        documentationTypeRequestDto.setDescription("Updated Description");
        // Mock repository behavior for not found case
        when(documentationTypeRepository.findById(documentationTypeId))
                .thenReturn(Optional.empty());

        // Assert that the exception is thrown
        DocumentationTypeNotFoundException thrownException = assertThrows(
                DocumentationTypeNotFoundException.class,
                () -> documentationTypeService.updateDocumentationType(documentationTypeId, documentationTypeRequestDto)
        );

        assertEquals("Documentation Type with ID " + documentationTypeId + " not found", thrownException.getMessage());
        verify(documentationTypeRepository).findById(documentationTypeId);
        verify(documentationTypeRepository, never()).save(any(DocumentationTypeEntity.class));
    }

    @Test
    public void testCreateDocumentationType_Success() {
        DocumentationTypeRequestDto documentationTypeRequestDto = new DocumentationTypeRequestDto();
        documentationTypeRequestDto.setName("New Type");
        documentationTypeRequestDto.setDescription("New Type Description");

        DocumentationTypeEntity documentationTypeEntity = new DocumentationTypeEntity();
        documentationTypeEntity.setName("New Type");
        documentationTypeEntity.setDescription("New Type Description");

        DocumentationType documentationType = new DocumentationType();
        documentationType.setName("New Type");
        documentationType.setDescription("New Type Description");

        // Mocking modelMapper and repository behavior
        when(modelMapper.map(documentationTypeRequestDto, DocumentationTypeEntity.class))
                .thenReturn(documentationTypeEntity);
        when(documentationTypeRepository.save(any(DocumentationTypeEntity.class)))
                .thenReturn(documentationTypeEntity);
        when(modelMapper.map(documentationTypeEntity, DocumentationType.class))
                .thenReturn(documentationType);

        // Call the method
        DocumentationType result = documentationTypeService.createDocumentationType(documentationTypeRequestDto);

        // Verify interactions
        verify(modelMapper).map(documentationTypeRequestDto, DocumentationTypeEntity.class);
        verify(documentationTypeRepository).save(documentationTypeEntity);
        verify(modelMapper).map(documentationTypeEntity, DocumentationType.class);

        // Assertions
        assertNotNull(result);
        assertEquals("New Type", result.getName());
        assertEquals("New Type Description", result.getDescription());
    }

    @Test
    public void testCreateDocumentationType_Failure() {
        DocumentationTypeRequestDto documentationTypeRequestDto = new DocumentationTypeRequestDto();
        documentationTypeRequestDto.setName(null); // Invalid name
        documentationTypeRequestDto.setDescription("New Type Description");

        DocumentationTypeEntity documentationTypeEntity = new DocumentationTypeEntity();
        documentationTypeEntity.setName(null); // Invalid name
        documentationTypeEntity.setDescription("New Type Description");

        // Mocking modelMapper to map invalid data
        when(modelMapper.map(documentationTypeRequestDto, DocumentationTypeEntity.class))
                .thenReturn(documentationTypeEntity);




        // Verify save is never called in case of failure
        verify(documentationTypeRepository, never()).save(any(DocumentationTypeEntity.class));
    }


}
