package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Custom exception to indicate that the documentation type was not found.
 */
public class DocumentationTypeNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new DocumentationTypeNotFoundException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public DocumentationTypeNotFoundException(String message) {
        super(message);
    }
}

