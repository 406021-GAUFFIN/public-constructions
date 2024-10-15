package ar.edu.utn.frc.tup.lc.iv.services.implementation;


import ar.edu.utn.frc.tup.lc.iv.dtos.worker.WorkerSpecialityRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.worker.WorkerSpecialityEntity;
import ar.edu.utn.frc.tup.lc.iv.error.WorkerSpecialityNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.worker.WorkerSpeciality;
import ar.edu.utn.frc.tup.lc.iv.repositories.NoteRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.WorkerSpecialityRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
public class WorkerSpecialityServiceImplTest {

    @MockBean
    private WorkerSpecialityRepository workerSpecialityRepository;

    @Autowired
    private WorkerSpecialityServiceImpl workerSpecialityService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testGetWorkerSpecialityList() {
        // Arrange
        WorkerSpecialityEntity workerSpecialityEntity1 = new WorkerSpecialityEntity();
        workerSpecialityEntity1.setId(1L);
        workerSpecialityEntity1.setName("Electrician");

        WorkerSpecialityEntity workerSpecialityEntity2 = new WorkerSpecialityEntity();
        workerSpecialityEntity2.setId(2L);
        workerSpecialityEntity2.setName("Plumber");

        List<WorkerSpecialityEntity> entityList = Arrays.asList(workerSpecialityEntity1, workerSpecialityEntity2);

        WorkerSpeciality workerSpeciality1 = new WorkerSpeciality();
        workerSpeciality1.setId(1L);
        workerSpeciality1.setName("Electrician");

        WorkerSpeciality workerSpeciality2 = new WorkerSpeciality();
        workerSpeciality2.setId(2L);
        workerSpeciality2.setName("Plumber");

        when(workerSpecialityRepository.findAll()).thenReturn(entityList);


        // Act
        List<WorkerSpeciality> result = workerSpecialityService.getWorkerSpecialityList();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Electrician", result.get(0).getName());
        assertEquals("Plumber", result.get(1).getName());
    }


    @Test
    void testGetWorkerSpecialityById_Success() {
        // Arrange
        Long id = 1L;

        WorkerSpecialityEntity workerSpecialityEntity = new WorkerSpecialityEntity();
        workerSpecialityEntity.setId(id);
        workerSpecialityEntity.setName("Electrician");

        WorkerSpeciality workerSpeciality = new WorkerSpeciality();
        workerSpeciality.setId(id);
        workerSpeciality.setName("Electrician");

        when(workerSpecialityRepository.findById(id)).thenReturn(Optional.of(workerSpecialityEntity));

        // Act
        WorkerSpeciality result = workerSpecialityService.getWorkerSpecialityById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Electrician", result.getName());

        verify(workerSpecialityRepository).findById(id);

    }

    @Test
    void testGetWorkerSpecialityById_NotFound() {
        // Arrange
        Long id = 2L;
        when(workerSpecialityRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        WorkerSpecialityNotFoundException exception = assertThrows(
                WorkerSpecialityNotFoundException.class,
                () -> workerSpecialityService.getWorkerSpecialityById(id)
        );

        assertEquals("WorkerSpeciality with ID " + id + " not found", exception.getMessage());
        verify(workerSpecialityRepository).findById(id);
    }


    @Test
    void testCreateWorkerSpeciality() {
        // Arrange
        WorkerSpecialityRequestDto requestDto = new WorkerSpecialityRequestDto();
        requestDto.setName("Electrician");
        requestDto.setDescription("Handles electrical work");

        WorkerSpecialityEntity workerSpecialityEntity = new WorkerSpecialityEntity();
        workerSpecialityEntity.setName("Electrician");
        workerSpecialityEntity.setDescription("Handles electrical work");

        WorkerSpecialityEntity savedWorkerSpecialityEntity = new WorkerSpecialityEntity();
        savedWorkerSpecialityEntity.setId(1L);
        savedWorkerSpecialityEntity.setName("Electrician");
        savedWorkerSpecialityEntity.setDescription("Handles electrical work");

        WorkerSpeciality workerSpeciality = new WorkerSpeciality();
        workerSpeciality.setId(1L);
        workerSpeciality.setName("Electrician");
        workerSpeciality.setDescription("Handles electrical work");


        when(workerSpecialityRepository.save(any(WorkerSpecialityEntity.class))).thenReturn(savedWorkerSpecialityEntity);


        // Act
        WorkerSpeciality result = workerSpecialityService.createWorkerSpeciality(requestDto);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Electrician", result.getName());
        assertEquals("Handles electrical work", result.getDescription());


        verify(workerSpecialityRepository).save(any(WorkerSpecialityEntity.class));

    }

    @Test
    void testUpdateWorkerSpeciality_Success() {
        // Arrange
        Long id = 1L;
        WorkerSpecialityRequestDto requestDto = new WorkerSpecialityRequestDto();
        requestDto.setName("Updated Electrician");
        requestDto.setDescription("Updated description");

        WorkerSpecialityEntity workerSpecialityEntity = new WorkerSpecialityEntity();
        workerSpecialityEntity.setId(id);
        workerSpecialityEntity.setName("Electrician");
        workerSpecialityEntity.setDescription("Handles electrical work");

        WorkerSpecialityEntity updatedWorkerSpecialityEntity = new WorkerSpecialityEntity();
        updatedWorkerSpecialityEntity.setId(id);
        updatedWorkerSpecialityEntity.setName("Updated Electrician");
        updatedWorkerSpecialityEntity.setDescription("Updated description");

        WorkerSpeciality updatedWorkerSpeciality = new WorkerSpeciality();
        updatedWorkerSpeciality.setId(id);
        updatedWorkerSpeciality.setName("Updated Electrician");
        updatedWorkerSpeciality.setDescription("Updated description");

        when(workerSpecialityRepository.findById(id)).thenReturn(Optional.of(workerSpecialityEntity));
        when(workerSpecialityRepository.save(workerSpecialityEntity)).thenReturn(updatedWorkerSpecialityEntity);


        // Act
        WorkerSpeciality result = workerSpecialityService.updateWorkerSpeciality(id, requestDto);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Electrician", result.getName());
        assertEquals("Updated description", result.getDescription());

        verify(workerSpecialityRepository).findById(id);
        verify(workerSpecialityRepository).save(workerSpecialityEntity);

    }

    @Test
    void testUpdateWorkerSpeciality_NotFound() {
        // Arrange
        Long id = 1L;
        WorkerSpecialityRequestDto requestDto = new WorkerSpecialityRequestDto();
        when(workerSpecialityRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        WorkerSpecialityNotFoundException exception = assertThrows(
                WorkerSpecialityNotFoundException.class,
                () -> workerSpecialityService.updateWorkerSpeciality(id, requestDto)
        );

        assertEquals("WorkerSpeciality with ID " + id + " not found", exception.getMessage());
        verify(workerSpecialityRepository).findById(id);
        verify(workerSpecialityRepository, never()).save(any());
    }

    @Test
    void testDeleteWorkerSpecialityById_Success() {
        // Arrange
        Long id = 1L;
        WorkerSpecialityEntity workerSpecialityEntity = new WorkerSpecialityEntity();
        workerSpecialityEntity.setId(id);

        when(workerSpecialityRepository.findById(id)).thenReturn(Optional.of(workerSpecialityEntity));

        // Act
        workerSpecialityService.deleteWorkerSpecialityById(id);

        // Assert
        verify(workerSpecialityRepository).findById(id);
        verify(workerSpecialityRepository).deleteById(id);
    }

    @Test
    void testDeleteWorkerSpecialityById_NotFound() {
        // Arrange
        Long id = 1L;
        when(workerSpecialityRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        WorkerSpecialityNotFoundException exception = assertThrows(
                WorkerSpecialityNotFoundException.class,
                () -> workerSpecialityService.deleteWorkerSpecialityById(id)
        );

        assertEquals("WorkerSpeciality with ID " + id + " not found", exception.getMessage());
        verify(workerSpecialityRepository).findById(id);
        verify(workerSpecialityRepository, never()).deleteById(id);
    }
}
