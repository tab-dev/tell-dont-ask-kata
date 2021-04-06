package it.gabrieletondi.telldontaskkata.domain.exception;

public class OrderShipmentException extends RuntimeException {
    public OrderShipmentException() {
        super();
    }

    public OrderShipmentException(String message) {
        super(message);
    }

    public OrderShipmentException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderShipmentException(Throwable cause) {
        super(cause);
    }

    protected OrderShipmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
