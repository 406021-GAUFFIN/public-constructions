package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for the ConstructionServiceImpl class.
 * This class tests the methods responsible for
 * registering constructions and updating their statuses.
 */
@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
class ConstructionServiceImplTest {
    @MockBean
    private ConstructionRepository constructionRepository;

    @MockBean
    private CadastreClient cadastreClient;

    @Autowired
    private ConstructionServiceImpl constructionService;
    @Qualifier("modelMapper")
    @Autowired
    private ModelMapper modelMapper;



    /**
     * Tests the successful registration of a construction.
     * It verifies that the response contains the correct details after registration.
     */
    @Test
    void registerConstruction_SuccessCase() {
        // Given
        ConstructionRequestDto constructionRequest = new ConstructionRequestDto();
        constructionRequest.setPlotId(1L);
        constructionRequest.setPlannedStartDate(new Date());
        constructionRequest.setPlannedEndDate(new Date());
        constructionRequest.setDescription("Description");
        constructionRequest.setProjectName("Project Name");
        constructionRequest.setProjectAddress("Project Address");

        ConstructionEntity constructionEntity = new ConstructionEntity();
        constructionEntity.setId(1L);
        constructionEntity.setPlotId(1L);
        constructionEntity.setPlannedStartDate(constructionRequest.getPlannedStartDate());
        constructionEntity.setPlannedEndDate(constructionRequest.getPlannedEndDate());
        constructionEntity.setDescription(constructionRequest.getDescription());
        constructionEntity.setProjectName(constructionRequest.getProjectName());
        constructionEntity.setProjectAddress(constructionRequest.getProjectAddress());
        constructionEntity.setApprovedByMunicipality(false);
        constructionEntity.setConstructionStatus(ConstructionStatus.PLANNED);

        // When
        when(cadastreClient.plotExists(constructionRequest.getPlotId())).thenReturn(true);
        when(constructionRepository.findByPlotId(constructionRequest.getPlotId())).thenReturn(Optional.empty());
        when(constructionRepository.save(any(ConstructionEntity.class))).thenReturn(constructionEntity);

        // Then
        ConstructionResponseDto actualResponse = constructionService.registerConstruction(constructionRequest);

        assertNotNull(actualResponse);

        assertEquals(constructionEntity.getProjectName(), actualResponse.getProjectName());
        assertEquals(constructionEntity.getPlotId(), actualResponse.getPlotId());

        verify(cadastreClient).plotExists(constructionRequest.getPlotId());
        verify(constructionRepository).findByPlotId(constructionRequest.getPlotId());
        verify(constructionRepository).save(any(ConstructionEntity.class));
    }

    /**
     * Tests the successful update of a construction's status.
     * It verifies that the construction status is updated correctly and that the correct message is returned.
     */
    @Test
    public void testUpdateConstructionStatus_Success() {
        Long constructionId = 1L;
        ConstructionEntity existingConstruction = new ConstructionEntity();
        existingConstruction.setConstructionStatus(ConstructionStatus.PLANNED);

        ConstructionEntity updatedConstruction = new ConstructionEntity();
        updatedConstruction.setConstructionStatus(ConstructionStatus.IN_PROGRESS);


        ConstructionUpdateStatusRequestDto requestDto = new ConstructionUpdateStatusRequestDto();
        requestDto.setConstructionId(constructionId);
        requestDto.setStatus(ConstructionStatus.IN_PROGRESS);

        when(constructionRepository.findById(constructionId)).thenReturn(Optional.of(existingConstruction));
        when(constructionRepository.save(any(ConstructionEntity.class))).thenReturn(updatedConstruction);

        ConstructionResponseDto response = constructionService.updateConstructionStatus(requestDto);

        assertNotNull(response);
        assertEquals(ConstructionStatus.IN_PROGRESS, response.getConstructionStatus());
        assertEquals(ConstructionStatus.IN_PROGRESS, existingConstruction.getConstructionStatus());
        verify(constructionRepository).save(existingConstruction); // Verify that save was called
    }

    /**
     * Tests the scenario where a construction is not found when attempting to update its status.
     * It verifies that the correct exception is thrown with the appropriate message.
     */
    @Test
    public void testUpdateConstructionStatus_ConstructionNotFound() {
        Long constructionId = 2L;
        ConstructionUpdateStatusRequestDto requestDto = new ConstructionUpdateStatusRequestDto();
        requestDto.setConstructionId(constructionId);
        requestDto.setStatus(ConstructionStatus.IN_PROGRESS);

        when(constructionRepository.findById(constructionId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ConstructionNotFoundException.class, () -> {
            constructionService.updateConstructionStatus(requestDto);
        });

        assertEquals("Construction with ID " + constructionId + " not found.", exception.getMessage());
    }

    @Test
    void getAllConstructions_SuccessCase() {
        // Given
        ConstructionEntity constructionEntity1 = new ConstructionEntity();
        constructionEntity1.setId(1L);
        constructionEntity1.setProjectName("Project 1");
        constructionEntity1.setPlotId(1L);
        constructionEntity1.setPlannedStartDate(new Date());
        constructionEntity1.setPlannedEndDate(new Date());

        ConstructionEntity constructionEntity2 = new ConstructionEntity();
        constructionEntity2.setId(2L);
        constructionEntity2.setProjectName("Project 2");
        constructionEntity2.setPlotId(2L);
        constructionEntity2.setPlannedStartDate(new Date());
        constructionEntity2.setPlannedEndDate(new Date());

        List<ConstructionEntity> constructionEntities = Arrays.asList(constructionEntity1, constructionEntity2);

        // Mocking repository response
        when(constructionRepository.findAll()).thenReturn(constructionEntities);




        // When
        List<ConstructionResponseDto> actualResponse = constructionService.getAllConstructions();

        // Then
        assertNotNull(actualResponse);
        assertEquals(2, actualResponse.size());
        // Add more assertions to check the contents if necessary
    }

    @Test
    void getConstructionById_SuccessCase() {
        // Given
        Long constructionId = 1L;
        ConstructionEntity constructionEntity = new ConstructionEntity();
        constructionEntity.setId(constructionId);
        constructionEntity.setProjectName("Project 1");
        constructionEntity.setPlotId(1L);

        ConstructionResponseDto constructionResponse = new ConstructionResponseDto();
        constructionResponse.setId(constructionId);
        constructionResponse.setProjectName("Project 1");

        // Mocking repository and ModelMapper behavior
        when(constructionRepository.findById(constructionId)).thenReturn(Optional.of(constructionEntity));


        // When
        ConstructionResponseDto actualResponse = constructionService.getConstructionById(constructionId);

        // Then
        assertNotNull(actualResponse);
        assertEquals(constructionId, actualResponse.getId());
        assertEquals("Project 1", actualResponse.getProjectName());
    }

    /**
     * Tests the scenario where a construction is not found by the given ID.
     * It verifies that the correct exception is thrown.
     */
    @Test
    void getConstructionById_ConstructionNotFound() {
        // Given
        Long constructionId = 2L;

        // Mocking repository to return empty when the ID does not exist
        when(constructionRepository.findById(constructionId)).thenReturn(Optional.empty());

        // Then
        Exception exception = assertThrows(RuntimeException.class, () -> {
            constructionService.getConstructionById(constructionId);
        });

        assertEquals("Construction not found with ID: " + constructionId, exception.getMessage());
    }

    @Test
    void getAllConstructionsPageable_SuccessCase() {
        // Given
        Pageable pageable = PageRequest.of(0, 10); // First page, 10 items per page
        List<ConstructionStatus> statuses = List.of(ConstructionStatus.PLANNED, ConstructionStatus.IN_PROGRESS);

        ConstructionEntity constructionEntity1 = new ConstructionEntity();
        constructionEntity1.setId(1L);
        constructionEntity1.setProjectName("Project 1");
        constructionEntity1.setConstructionStatus(ConstructionStatus.PLANNED);

        ConstructionEntity constructionEntity2 = new ConstructionEntity();
        constructionEntity2.setId(2L);
        constructionEntity2.setProjectName("Project 2");
        constructionEntity2.setConstructionStatus(ConstructionStatus.IN_PROGRESS);

        Page<ConstructionEntity> constructionEntityPage = new PageImpl<>(List.of(constructionEntity1, constructionEntity2), pageable, 2);

        ConstructionResponseDto constructionResponseDto1 = new ConstructionResponseDto();
        constructionResponseDto1.setId(1L);
        constructionResponseDto1.setProjectName("Project 1");
        constructionResponseDto1.setConstructionStatus(ConstructionStatus.PLANNED);

        ConstructionResponseDto constructionResponseDto2 = new ConstructionResponseDto();
        constructionResponseDto2.setId(2L);
        constructionResponseDto2.setProjectName("Project 2");
        constructionResponseDto2.setConstructionStatus(ConstructionStatus.IN_PROGRESS);

        // When
        when(constructionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(constructionEntityPage);

        // Then
        Page<ConstructionResponseDto> actualResponse = constructionService.getAllConstructionsPageable(pageable, statuses);

        assertNotNull(actualResponse);
        assertEquals(2, actualResponse.getTotalElements()); // Total number of elements
        assertEquals(2, actualResponse.getContent().size()); // Number of elements on the page
        assertEquals("Project 1", actualResponse.getContent().get(0).getProjectName());
        assertEquals("Project 2", actualResponse.getContent().get(1).getProjectName());
    }

    /**
     * Tests the pageable retrieval with no matching constructions.
     * This tests the scenario where the repository returns an empty page.
     */
    @Test
    void getAllConstructionsPageable_EmptyResult() {
        // Given
        Pageable pageable = PageRequest.of(0, 10); // First page, 10 items per page
        List<ConstructionStatus> statuses = List.of(ConstructionStatus.COMPLETED);

        // Empty result page
        Page<ConstructionEntity> emptyPage = new PageImpl<>(List.of(), pageable, 0);

        // When
        when(constructionRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(emptyPage);

        // Then
        Page<ConstructionResponseDto> actualResponse = constructionService.getAllConstructionsPageable(pageable, statuses);

        assertNotNull(actualResponse);
        assertEquals(0, actualResponse.getTotalElements());
        assertEquals(0, actualResponse.getContent().size()); // No elements on the page
    }



    @Test
    void updateConstructionsDetails_success() {
        // given
        Long constructionId = 1L;
        ConstructionEntity existingConstruction = new ConstructionEntity();
        existingConstruction.setId(constructionId);
        existingConstruction.setPlannedStartDate(new Date());
        existingConstruction.setPlannedEndDate(new Date());
        existingConstruction.setDescription("old description");
        existingConstruction.setProjectName("old project name");

        ConstructionUpdateDto updateDto = new ConstructionUpdateDto();
        updateDto.setPlannedStartDate(new Date());
        updateDto.setPlannedEndDate(new Date());
        updateDto.setDescription("new description");
        updateDto.setProjectName("new project name");

        when(constructionRepository.findById(constructionId)).thenReturn(Optional.of(existingConstruction));
        when(constructionRepository.save(any(ConstructionEntity.class))).thenReturn(existingConstruction);

        // when
        ConstructionResponseDto responseDto = constructionService.updateConstructionDetails(constructionId, updateDto);

        // then
        assertNotNull(responseDto);
        assertEquals("new description", existingConstruction.getDescription());
        assertEquals("new project name", existingConstruction.getProjectName());

        verify(constructionRepository).findById(constructionId);
        verify(constructionRepository).save(existingConstruction);

    }

    @Test
    void updateConstructionsDetails_notFound() {
        // given
        Long constructionId = 1L;
        ConstructionUpdateDto updateDto = new ConstructionUpdateDto();
        updateDto.setPlannedStartDate(new Date());
        updateDto.setPlannedEndDate(new Date());
        updateDto.setDescription("some description");
        updateDto.setProjectName("some project name");

        when(constructionRepository.findById(constructionId)).thenReturn(Optional.empty());

        // when y then
        assertThrows(ConstructionNotFoundException.class, () -> {
           constructionService.updateConstructionDetails(constructionId, updateDto);
        });

        verify(constructionRepository).findById(constructionId);

    }

}

