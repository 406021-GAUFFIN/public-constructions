package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.models.construction.ConstructionStatus;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class ConstructionServiceImplTest {
    @MockBean
    private ConstructionRepository constructionRepository;

    @Autowired
    private ConstructionServiceImpl constructionService;

    @Test
    void registerConstruction_SuccessCase() {
        // Given
        ConstructionRequestDto constructionRequest = new ConstructionRequestDto();
        constructionRequest.setOwnerId(1L);
        constructionRequest.setPlotId(1L);
        constructionRequest.setPlannedStartDate(new Date());
        constructionRequest.setPlannedEndDate(new Date());
        constructionRequest.setDescription("Description");
        constructionRequest.setProjectName("Project Name");
        constructionRequest.setProjectAddress("Project Address");

        ConstructionEntity constructionEntity = new ConstructionEntity();
        constructionEntity.setId(1L);
        constructionEntity.setOwnerId(1L);
        constructionEntity.setPlotId(1L);
        constructionEntity.setPlannedStartDate(constructionRequest.getPlannedStartDate());
        constructionEntity.setPlannedEndDate(constructionRequest.getPlannedEndDate());
        constructionEntity.setDescription(constructionRequest.getDescription());
        constructionEntity.setProjectName(constructionRequest.getProjectName());
        constructionEntity.setProjectAddress(constructionRequest.getProjectAddress());
        constructionEntity.setApprovedByMunicipality(false);
        constructionEntity.setConstructionStatus(ConstructionStatus.IN_PROGRESS);

        // When
        when(constructionRepository.findByPlotId(constructionRequest.getPlotId())).thenReturn(Optional.empty());
        when(constructionRepository.save(any(ConstructionEntity.class))).thenReturn(constructionEntity);

        // Then
        ConstructionResponseDto response = constructionService.registerConstruction(constructionRequest);
        assertNotNull(response);
        assertEquals(constructionRequest.getPlotId(), response.getPlotId());
        assertEquals("Description", response.getDescription());
        assertEquals("Project Name", response.getProjectName());
    }
}
