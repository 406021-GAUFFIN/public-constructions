package ar.edu.utn.frc.tup.lc.iv.error;


import java.io.Serial;

/**
 * Exception thrown when a construction project is not found.
 */
public class ConstructionNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new ConstructionNotFoundException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public ConstructionNotFoundException(String message) {
        super(message);
    }
}
