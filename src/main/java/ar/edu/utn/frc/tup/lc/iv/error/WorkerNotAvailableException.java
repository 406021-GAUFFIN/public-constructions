package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

public class WorkerNotAvailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public WorkerNotAvailableException(String message) {
        super(message);
    }
}
