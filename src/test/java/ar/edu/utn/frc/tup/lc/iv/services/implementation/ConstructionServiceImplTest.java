package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.clients.CadastreClient;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.error.ConstructionNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.WorkerDocumentationService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
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
        existingConstruction.setConstructionStatus(ConstructionStatus.PLANNED); // Set an initial status

        ConstructionUpdateStatusRequestDto requestDto = new ConstructionUpdateStatusRequestDto();
        requestDto.setConstructionId(constructionId);
        requestDto.setStatus(ConstructionStatus.IN_PROGRESS);

        when(constructionRepository.findById(constructionId)).thenReturn(Optional.of(existingConstruction));

        ConstructionUpdateStatusResponseDto response = constructionService.updateConstructionStatus(requestDto);

        assertNotNull(response);
        assertEquals("Construction updated with ID " + constructionId, response.getMessage());
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


}

