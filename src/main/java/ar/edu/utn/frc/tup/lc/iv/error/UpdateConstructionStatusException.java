package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when there is an error updating
 * the status of a construction project.
 */
public class UpdateConstructionStatusException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new UpdateConstructionStatusException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public UpdateConstructionStatusException(String message) {
        super(message);
    }
}
