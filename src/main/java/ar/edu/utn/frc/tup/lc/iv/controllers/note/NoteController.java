package ar.edu.utn.frc.tup.lc.iv.controllers.note;

import ar.edu.utn.frc.tup.lc.iv.controllers.api_response.ApiResponseConstants;
import ar.edu.utn.frc.tup.lc.iv.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lc.iv.dtos.note.NoteRequestDto;
import ar.edu.utn.frc.tup.lc.iv.models.note.Note;
import ar.edu.utn.frc.tup.lc.iv.services.interfaces.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Controller for managing worker specialties operations.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {
    /**
     * Service for handling notes logic.
     */
    private final NoteService noteService;

    /**
     * Retrieves a list of all notes.
     *
     * @return A list of notes.
     */
    @Operation(
            summary = "Get all notes",
            description = "Retrieves a list of all notes available"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = Note.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @GetMapping("/list")
    public ResponseEntity<List<Note>> getNoteList() {
        return ResponseEntity.ok(noteService.getNoteList());
    }

    /**
     * Retrieves a note by its ID.
     *
     * @param id The ID of the note to retrieve.
     * @return The note.
     */
    @Operation(
            summary = "Get a note by ID",
            description = "Retrieves a specific note based on its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = Note.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.BAD_REQUEST,
                    description = ApiResponseConstants.BAD_REQUEST_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }

    /**
     * Creates a new note based on the provided request DTO.
     *
     * @param noteRequestDto The DTO containing note details.
     * @return The created note.
     */
    @Operation(
            summary = "Create a new note",
            description = "Creates a new note by providing the necessary details"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = Note.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.BAD_REQUEST,
                    description = ApiResponseConstants.BAD_REQUEST_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody NoteRequestDto noteRequestDto) {
        return ResponseEntity.ok(noteService.createNote(noteRequestDto));
    }

    /**
     * Updates an existing note with the provided details.
     *
     * @param id       The ID of the note to update.
     * @param noteRequestDto The DTO containing the updated note details.
     * @return The updated note.
     */
    @Operation(
            summary = "Update an existing note",
            description = "Updates an existing note by providing the necessary details"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.OK,
                    description = ApiResponseConstants.OK_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = Note.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.BAD_REQUEST,
                    description = ApiResponseConstants.BAD_REQUEST_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.NOT_FOUND,
                    description = ApiResponseConstants.NOT_FOUND_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody NoteRequestDto noteRequestDto) {
        return ResponseEntity.ok(noteService.updateNote(id, noteRequestDto));
    }

    /**
     * Deletes a note by its ID.
     *
     * @param id The ID of the note to delete.
     */
    @Operation(
            summary = "Delete a note by ID",
            description = "Deletes a specific note based on its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = ApiResponseConstants.NO_CONTENT,
                    description = ApiResponseConstants.NO_CONTENT_MESSAGE
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.BAD_REQUEST,
                    description = ApiResponseConstants.BAD_REQUEST_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            ),
            @ApiResponse(
                    responseCode = ApiResponseConstants.INTERNAL_SERVER_ERROR,
                    description = ApiResponseConstants.INTERNAL_SERVER_ERROR_MESSAGE,
                    content = @Content(
                            schema = @Schema(implementation = ErrorApi.class)
                    )
            )
    })
    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }
}
