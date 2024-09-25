package ar.edu.utn.frc.tup.lc.iv.controllers.construction;

import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionResponseDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusRequestDto;
import ar.edu.utn.frc.tup.lc.iv.dtos.construction.ConstructionUpdateStatusResponseDto;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.ConstructionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ConstructionControllerTest {

    @Mock
    private ConstructionService constructionService;

    @InjectMocks
    private ConstructionController constructionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createConstruction_succes() {
        //given
        ConstructionRequestDto requestDto = new ConstructionRequestDto();
        ConstructionResponseDto responseDto = new ConstructionResponseDto();

        when(constructionService.registerConstruction(any(ConstructionRequestDto.class)))
                .thenReturn(responseDto);

        //when
        ResponseEntity<ConstructionResponseDto> response = constructionController.createConstruction(requestDto);

        //then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDto, response.getBody());
        verify(constructionService, times(1)).registerConstruction(requestDto);

    }

    @Test
    void updateConstructionStatus_succes() {
        //given
        ConstructionUpdateStatusRequestDto requestDto = new ConstructionUpdateStatusRequestDto();
        ConstructionUpdateStatusResponseDto responseDto = new ConstructionUpdateStatusResponseDto();
        requestDto.setConstructionId(1L);
        when(constructionService.updateConstructionStatus(any(ConstructionUpdateStatusRequestDto.class)))
                .thenReturn(responseDto);

        //when
        ConstructionUpdateStatusResponseDto response = constructionController.updateConstructionStatus(requestDto);

        //then
        assertEquals(responseDto, response);
        verify(constructionService, times(1)).updateConstructionStatus(requestDto);
    }
}