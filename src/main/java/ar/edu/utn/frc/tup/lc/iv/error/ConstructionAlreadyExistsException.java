package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Custom exception to indicate that a construction
 * already exists for a given plot.
 */
public class ConstructionAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new ConstructionAlreadyExistsException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public ConstructionAlreadyExistsException(String message) {
        super(message);
    }
}
