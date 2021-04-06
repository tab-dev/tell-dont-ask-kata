package it.gabrieletondi.telldontaskkata.domain.exception;

public class OrderRejectionException extends RuntimeException {

    public OrderRejectionException() {
        super();
    }

    public OrderRejectionException(String message) {
        super(message);
    }

    public OrderRejectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderRejectionException(Throwable cause) {
        super(cause);
    }

    protected OrderRejectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
