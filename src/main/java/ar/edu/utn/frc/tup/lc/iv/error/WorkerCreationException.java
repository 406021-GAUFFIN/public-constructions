package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

public class WorkerCreationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;
    public WorkerCreationException(String message) {
        super(message);
    }
}
