package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Custom exception to handle errors related to the Plot service.
 */
public class PlotServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new PlotServiceException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public PlotServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new PlotServiceException
     * with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause.
     */
    public PlotServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
