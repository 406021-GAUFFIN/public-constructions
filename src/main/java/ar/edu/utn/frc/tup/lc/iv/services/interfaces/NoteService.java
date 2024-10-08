package ar.edu.utn.frc.tup.lc.iv.services.interfaces;

import ar.edu.utn.frc.tup.lc.iv.dtos.note.NoteRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.note.Note;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for handling operations related to notes.
 */
@Service
public interface NoteService {

    /**
     * Retrieves a list of all notes.
     *
     * @return A list of Note representing
     * all available notes.
     */
    List<Note> getNoteList();

    /**
     * Retrieves a specific note by its ID.
     *
     * @param id The ID of the note to retrieve.
     * @return A Note representing the note
     * with the specified ID.
     */
    Note getNoteById(Long id);

    /**
     * Creates a new note.
     *
     * @param noteRequestDto The DTO containing the details of the
     *                       note to be created.
     * @return A Note representing the newly created note.
     */
    Note createNote(NoteRequestDto noteRequestDto);

    /**
     * Updates an existing note.
     *
     * @param id             The ID of the note to update.
     * @param noteRequestDto The DTO containing the updated details
     *                       of the note.
     * @return A Note representing the updated note.
     */
    Note updateNote(Long id, NoteRequestDto noteRequestDto);

    /**
     * Deletes a note by its ID.
     *
     * @param id The ID of the note to delete.
     */
    void deleteNoteById(Long id);
}
