package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when a worker creation fails.
 */
public class WorkerCreationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a WorkerCreationException
     * with the specified message.
     *
     * @param message Detail message for the exception.
     */
    public WorkerCreationException(String message) {
        super(message);
    }
}
