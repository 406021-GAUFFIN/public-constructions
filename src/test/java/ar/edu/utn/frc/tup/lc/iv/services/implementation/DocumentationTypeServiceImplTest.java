package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.documentation.DocumentationTypeRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.documentation.DocumentationTypeEntity;
import ar.edu.utn.frc.tup.lc.iv.error.DocumentationTypeNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.documentation.DocumentationType;
import ar.edu.utn.frc.tup.lc.iv.repositories.DocumentationTypeRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
class DocumentationTypeServiceImplTest {

    @MockBean
    private DocumentationTypeRepository documentationTypeRepository;

    @Autowired
    private DocumentationTypeServiceImpl documentationTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void getDocumentationTypeList_Ok() {
        DocumentationTypeEntity documentationTypeEntity1 = new DocumentationTypeEntity();
        documentationTypeEntity1.setId(1L);
        documentationTypeEntity1.setName("Type 1");

        DocumentationTypeEntity documentationTypeEntity2 = new DocumentationTypeEntity();
        documentationTypeEntity2.setId(2L);
        documentationTypeEntity2.setName("Type 2");

        List<DocumentationTypeEntity> documentationTypeEntities = new ArrayList<>();
        documentationTypeEntities.add(documentationTypeEntity1);
        documentationTypeEntities.add(documentationTypeEntity2);

        when(documentationTypeRepository.findAll()).thenReturn(documentationTypeEntities);

        List<DocumentationType> result = documentationTypeService.getDocumentationTypeList();

        assertEquals(2, result.size());
        assertEquals("Type 1", result.get(0).getName());
        assertEquals("Type 2", result.get(1).getName());

        verify(documentationTypeRepository).findAll();
    }

    @Test
    void getDocumentationTypeList_Empty() {
        when(documentationTypeRepository.findAll()).thenReturn(new ArrayList<>());

        List<DocumentationType> result = documentationTypeService.getDocumentationTypeList();
        assertEquals(0, result.size());
    }

    @Test
    void getDocumentationTypeById_Ok() {
        DocumentationTypeEntity documentationTypeEntity = new DocumentationTypeEntity();
        documentationTypeEntity.setId(1L);
        documentationTypeEntity.setName("Type 1");

        when(documentationTypeRepository.findById(1L)).thenReturn(Optional.of(documentationTypeEntity));

        DocumentationType result = documentationTypeService.getDocumentationTypeById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Type 1", result.getName());

        verify(documentationTypeRepository, times(1)).findById(1L);
    }

    @Test
    void getDocumentationTypeById_Not_Found() {
        when(documentationTypeRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(DocumentationTypeNotFoundException.class, () -> documentationTypeService.getDocumentationTypeById(2L));

        verify(documentationTypeRepository, times(1)).findById(2L);
    }

    @Test
    void createDocumentationType_Ok() {
        DocumentationTypeRequestDto documentationTypeRequestDto = new DocumentationTypeRequestDto();
        documentationTypeRequestDto.setDescription("Description");
        documentationTypeRequestDto.setName("Name");

        DocumentationTypeEntity documentationTypeEntity = modelMapper.map(documentationTypeRequestDto, DocumentationTypeEntity.class);
        documentationTypeEntity.setId(1L);

        when(documentationTypeRepository.save(any(DocumentationTypeEntity.class))).thenReturn(documentationTypeEntity);
        DocumentationType result = documentationTypeService.createDocumentationType(documentationTypeRequestDto);

        assertEquals(1L, result.getId());
        assertEquals("Name", result.getName());

        verify(documentationTypeRepository, times(1)).save(any(DocumentationTypeEntity.class));
    }

    @Test
    void updateDocumentationType_Ok() {
        DocumentationTypeEntity documentationTypeEntity = new DocumentationTypeEntity();
        documentationTypeEntity.setName("Name");
        documentationTypeEntity.setDescription("Description");

        DocumentationTypeRequestDto documentationTypeRequestDto = new DocumentationTypeRequestDto();
        documentationTypeRequestDto.setName("New name");
        documentationTypeRequestDto.setDescription("New description");

        DocumentationTypeEntity documentationTypeEntityUpdated = new DocumentationTypeEntity();
        documentationTypeEntityUpdated.setId(1L);
        documentationTypeEntityUpdated.setName("New name");
        documentationTypeEntityUpdated.setDescription("New description");

        when(documentationTypeRepository.findById(1L)).thenReturn(Optional.of(documentationTypeEntity));
        when(documentationTypeRepository.save(any(DocumentationTypeEntity.class))).thenReturn(documentationTypeEntityUpdated);

        DocumentationType result = documentationTypeService.updateDocumentationType(1L, documentationTypeRequestDto);

        assertEquals("New name", result.getName());
        assertEquals("New description", result.getDescription());

        verify(documentationTypeRepository, times(1)).findById(1L);
        verify(documentationTypeRepository, times(1)).save(any(DocumentationTypeEntity.class));
    }

    @Test
    void deleteDocumentationTypeById_Not_Found() {
        Long id = 1L;

        when(documentationTypeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(DocumentationTypeNotFoundException.class, () -> {
            documentationTypeService.deleteDocumentationTypeById(id);
        });

        verify(documentationTypeRepository, times(1)).findById(id);
    }

    @Test
    void deleteDocumentationTypeById_Ok() {
        DocumentationTypeEntity documentationTypeEntity = new DocumentationTypeEntity();
        documentationTypeEntity.setId(1L);
        documentationTypeEntity.setName("Name1");

        when(documentationTypeRepository.findById(1L)).thenReturn(Optional.of(documentationTypeEntity));
        doNothing().when(documentationTypeRepository).deleteById(1L);

        documentationTypeService.deleteDocumentationTypeById(1L);

        verify(documentationTypeRepository, times(1)).findById(1L);
        verify(documentationTypeRepository, times(1)).deleteById(1L);
    }
}