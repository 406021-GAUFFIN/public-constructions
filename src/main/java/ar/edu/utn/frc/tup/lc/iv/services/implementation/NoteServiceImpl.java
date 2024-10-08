package ar.edu.utn.frc.tup.lc.iv.services.implementation;

import ar.edu.utn.frc.tup.lc.iv.dtos.note.NoteRequestDto;
import ar.edu.utn.frc.tup.lc.iv.entities.note.NoteEntity;
import ar.edu.utn.frc.tup.lc.iv.error.NoteNotFoundException;
import ar.edu.utn.frc.tup.lc.iv.models.note.Note;
import ar.edu.utn.frc.tup.lc.iv.repositories.NoteRepository;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.NoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the NoteService interface.
 * Provides methods for managing notes.
 */
@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {
    /**
     * Repository for accessing note entities.
     */
    private final NoteRepository noteRepository;

    /**
     * ModelMapper instance for converting between between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Retrieves a list of all notes.
     *
     * @return A list of notes.
     */
    @Override
    public List<Note> getNoteList() {
        return noteRepository.findAll().stream()
                .map(noteEntity -> modelMapper.map(noteEntity, Note.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a note by its ID.
     *
     * @param id The ID of the note to retrieve.
     * @return The note.
     * @throws NoteNotFoundException if note is not found.
     */
    @Override
    public Note getNoteById(Long id) {
        NoteEntity noteEntity = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with ID " + id + " not found"));
        return modelMapper.map(noteEntity, Note.class);
    }

    /**
     * Creates a new note based on the provided request DTO.
     *
     * @param noteRequestDto The DTO containing note details.
     * @return The created note.
     */
    @Override
    public Note createNote(NoteRequestDto noteRequestDto) {
        NoteEntity noteEntity = modelMapper.map(noteRequestDto, NoteEntity.class);
        NoteEntity noteEntitySaved = noteRepository.save(noteEntity);

        return modelMapper.map(noteEntitySaved, Note.class);
    }

    /**
     * Updates an existing note with the provided details.
     *
     * @param id             The ID of the documentation type to update.
     * @param noteRequestDto The DTO containing the updated note details.
     * @return The updated note.
     * @throws NoteNotFoundException if the note is not found.
     */
    @Override
    public Note updateNote(Long id, NoteRequestDto noteRequestDto) {
        NoteEntity noteEntity = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with ID " + id + " not found"));

        modelMapper.map(noteRequestDto, noteEntity);

        NoteEntity noteEntitySaved = noteRepository.save(noteEntity);

        return modelMapper.map(noteEntitySaved, Note.class);
    }

    /**
     * Deletes a note by its ID.
     *
     * @param id The ID of the note to delete.
     * @throws NoteNotFoundException if the
     *                               note is not found.
     */
    @Override
    public void deleteNoteById(Long id) {
        noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note with ID " + id + " not found"));

        noteRepository.deleteById(id);
    }
}
