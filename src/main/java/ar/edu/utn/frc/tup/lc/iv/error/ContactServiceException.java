package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

/**
 * Exception thrown when there is
 * an error in the contact service.
 */
public class ContactServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ContactServiceException
     * with the specified detail message.
     *
     * @param message the detail message which is
     *                saved for later retrieval by the
     * {@link Throwable#getMessage()} method.
     */
    public ContactServiceException(String message) {
        super(message);
    }
}
