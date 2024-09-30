package ar.edu.utn.frc.tup.lc.iv.controllers.construction;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusResponseDto;
import ar.edu.utn.frc.tup.lc.iv.entities.construction.ConstructionEntity;
import ar.edu.utn.frc.tup.lc.iv.models.construction.Construction;
import ar.edu.utn.frc.tup.lc.iv.repositories.ConstructionRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
class ConstructionControllerTest {

    @Mock
    private ConstructionService constructionService;
    @Mock
    private ConstructionRepository constructionRepository;

    @InjectMocks
    private ConstructionController constructionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createConstruction_succes() {
//        //given
//        ConstructionRequestDto requestDto = new ConstructionRequestDto();
//        ConstructionResponseDto responseDto = new ConstructionResponseDto();
//        ConstructionEntity constructionEntity = new ConstructionEntity();
//
//
//        when(constructionService.registerConstruction(any(ConstructionRequestDto.class)))
//                .thenReturn(responseDto);
//        when(constructionRepository.save(any(ConstructionEntity.class))).thenReturn(constructionEntity);
//
//        //when
//        ResponseEntity<ConstructionResponseDto> response = constr.createConstruction(requestDto);
//
//        //then
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals(responseDto, response.getBody());
//        verify(constructionService, times(1)).registerConstruction(requestDto);

    }

    @Test
    void updateConstructionStatus_succes() {
//        //given
//        ConstructionUpdateStatusRequestDto requestDto = new ConstructionUpdateStatusRequestDto();
//        ConstructionUpdateStatusResponseDto responseDto = new ConstructionUpdateStatusResponseDto();
//        requestDto.setConstructionId(1L);
//        when(constructionService.updateConstructionStatus(any(ConstructionUpdateStatusRequestDto.class)))
//                .thenReturn(responseDto);
//
//        //when
//        ConstructionUpdateStatusResponseDto response = constructionController.updateConstructionStatus(requestDto);
//
//        //then
//        assertEquals(responseDto, response);
//        verify(constructionService, times(1)).updateConstructionStatus(requestDto);
    }
}