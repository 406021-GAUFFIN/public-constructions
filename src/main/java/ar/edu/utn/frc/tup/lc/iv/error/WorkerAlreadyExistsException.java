package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

public class WorkerAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public WorkerAlreadyExistsException(String message) {
        super(message);
    }
}
