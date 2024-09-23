package ar.edu.utn.frc.tup.lc.iv.error;



public class ConstructionNotFoundException extends RuntimeException {

    public ConstructionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public ConstructionNotFoundException(String message) {
        super(message);
    }
}
