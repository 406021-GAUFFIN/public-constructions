package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Custom exception to indicate that the note was not found.
 */
public class NoteNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new NoteNotFoundException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public NoteNotFoundException(String message) {
        super(message);
    }
}

