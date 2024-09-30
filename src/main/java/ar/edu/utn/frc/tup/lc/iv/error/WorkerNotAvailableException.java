package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when a worker is not available.
 */
public class WorkerNotAvailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    /**
     * Constructs a new WorkerNotAvailableException with the specified message.
     *
     * @param message the detail message
     */
    public WorkerNotAvailableException(String message) {
        super(message);
    }
}
