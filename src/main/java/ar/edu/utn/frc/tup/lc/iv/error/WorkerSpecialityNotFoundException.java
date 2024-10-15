package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Custom exception to indicate that the worker speciality was not found.
 */
public class WorkerSpecialityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new WorkerSpecialityNotFoundException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public WorkerSpecialityNotFoundException(String message) {
        super(message);
    }
}

