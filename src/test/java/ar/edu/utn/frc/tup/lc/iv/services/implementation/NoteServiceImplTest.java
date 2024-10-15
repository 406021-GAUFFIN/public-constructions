package ar.edu.utn.frc.tup.lc.iv.services.implementation;


import ar.edu.utn.frc.tup.lc.iv.dtos.note.NoteRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.note.NoteEntity;
import ar.edu.utn.frc.tup.lc.iv.error.NoteNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.note.Note;
import ar.edu.utn.frc.tup.lc.iv.repositories.DocumentationTypeRepository;
import ar.edu.utn.frc.tup.lc.iv.repositories.NoteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(properties = {
        "cadastre.url = http://localhost:8080",
        "contacts.url = http://localhost:8081",
        "accesses.url = http://localhost:8085"
})
public class NoteServiceImplTest {
    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteServiceImpl noteService;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    void testGetNoteList() {
        // Mock NoteEntity objects
        NoteEntity noteEntity1 = new NoteEntity();
        noteEntity1.setDescription("Test note 1");
        noteEntity1.setUserId(1L);

        NoteEntity noteEntity2 = new NoteEntity();
        noteEntity2.setDescription("Test note 2");
        noteEntity2.setUserId(2L);

        // Mock Note objects
        Note note1 = new Note();
        note1.setDescription("Test note 1");
        note1.setUserId(1L);

        Note note2 = new Note();
        note2.setDescription("Test note 2");
        note2.setUserId(2L);

        // Mock repository and modelMapper behavior
        when(noteRepository.findAll()).thenReturn(Arrays.asList(noteEntity1, noteEntity2));


        // Call the service method
        List<Note> result = noteService.getNoteList();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Test note 1", result.get(0).getDescription());
        assertEquals("Test note 2", result.get(1).getDescription());
    }

    @Test
    void testGetNoteById_Success() {
        // Mock NoteEntity
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setDescription("Test note");
        noteEntity.setUserId(1L);

        // Mock Note
        Note note = new Note();
        note.setDescription("Test note");
        note.setUserId(1L);

        // Mock repository and modelMapper behavior
        when(noteRepository.findById(1L)).thenReturn(Optional.of(noteEntity));


        // Call the service method
        Note result = noteService.getNoteById(1L);

        // Verify the result
        assertNotNull(result);
        assertEquals("Test note", result.getDescription());
        assertEquals(1L, result.getUserId());
    }

    @Test
    void testGetNoteById_NotFound() {
        // Mock repository behavior
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the exception is thrown
        NoteNotFoundException exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.getNoteById(1L);
        });

        assertEquals("Note with ID 1 not found", exception.getMessage());
    }

    @Test
    void testCreateNote() {
        // Mock NoteRequestDto
        NoteRequestDto noteRequestDto = new NoteRequestDto();
        noteRequestDto.setDescription("New note");

        // Mock NoteEntity (before save)
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setDescription("New note");

        // Mock NoteEntity (after save)
        NoteEntity savedNoteEntity = new NoteEntity();
        savedNoteEntity.setId(1L); // Simulate the ID generated on save
        savedNoteEntity.setDescription("New note");

        // Mock Note (the returned model)
        Note note = new Note();
        note.setId(1L);
        note.setDescription("New note");

        // Mock ModelMapper and repository behavior

        when(noteRepository.save(any(NoteEntity.class))).thenReturn(savedNoteEntity);


        // Call the service method
        Note result = noteService.createNote(noteRequestDto);

        // Verify the result
        assertEquals(1L, result.getId());
        assertEquals("New note", result.getDescription());
    }

    @Test
    void testUpdateNote_Success() {
        // Mock NoteRequestDto
        NoteRequestDto noteRequestDto = new NoteRequestDto();
        noteRequestDto.setDescription("Updated note");

        // Mock existing NoteEntity
        NoteEntity existingNoteEntity = new NoteEntity();
        existingNoteEntity.setId(1L);
        existingNoteEntity.setDescription("Original note");

        // Mock updated NoteEntity
        NoteEntity updatedNoteEntity = new NoteEntity();
        updatedNoteEntity.setId(1L);
        updatedNoteEntity.setDescription("Updated note");

        // Mock the returned Note model
        Note updatedNote = new Note();
        updatedNote.setId(1L);
        updatedNote.setDescription("Updated note");

        // Mock repository and modelMapper behavior
        when(noteRepository.findById(1L)).thenReturn(Optional.of(existingNoteEntity));
        when(noteRepository.save(existingNoteEntity)).thenReturn(updatedNoteEntity);



        // Call the service method
        Note result = noteService.updateNote(1L, noteRequestDto);

        // Verify the result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Updated note", result.getDescription());
    }

    @Test
    void testUpdateNote_NotFound() {
        // Mock repository behavior
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the exception is thrown
        NoteNotFoundException exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.updateNote(1L, new NoteRequestDto());
        });

        assertEquals("Note with ID 1 not found", exception.getMessage());
    }

    @Test
    void testDeleteNoteById_Success() {
        // Mock existing NoteEntity
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setId(1L);

        // Mock repository behavior
        when(noteRepository.findById(1L)).thenReturn(Optional.of(noteEntity));

        // Call the service method
        noteService.deleteNoteById(1L);

        // Verify that deleteById was called
        verify(noteRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteNoteById_NotFound() {
        // Mock repository behavior for note not found
        when(noteRepository.findById(1L)).thenReturn(Optional.empty());

        // Verify that the exception is thrown
        NoteNotFoundException exception = assertThrows(NoteNotFoundException.class, () -> {
            noteService.deleteNoteById(1L);
        });

        assertEquals("Note with ID 1 not found", exception.getMessage());

        // Verify that deleteById was never called
        verify(noteRepository, never()).deleteById(1L);
    }


}

