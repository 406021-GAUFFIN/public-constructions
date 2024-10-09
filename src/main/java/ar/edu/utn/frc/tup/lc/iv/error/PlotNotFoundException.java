package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Custom exception to indicate that the plot was not found.
 */
public class PlotNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L; // Serial version UID for serialization

    /**
     * Constructs a new PlotNotFoundException
     * with the specified detail message.
     *
     * @param message the detail message.
     */
    public PlotNotFoundException(String message) {
        super(message);
    }
}

