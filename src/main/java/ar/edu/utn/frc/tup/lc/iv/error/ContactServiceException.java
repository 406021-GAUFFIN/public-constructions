package ar.edu.utn.frc.tup.lc.iv.error;

import java.io.Serial;

public class ContactServiceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;


    public ContactServiceException(String message) {
        super(message);
    }
}
