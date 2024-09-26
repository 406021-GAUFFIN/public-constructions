package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when a worker
 * already exists in the system.
 */
public class WorkerAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new WorkerAlreadyExistsException
     * with the specified detail message.
     *
     * @param message the detail message which is saved
     *               for later retrieval by the
     * {@link Throwable#getMessage()} method.
     */
    public WorkerAlreadyExistsException(String message) {
        super(message);
    }
}
