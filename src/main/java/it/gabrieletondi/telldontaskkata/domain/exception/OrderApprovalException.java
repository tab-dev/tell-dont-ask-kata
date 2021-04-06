package it.gabrieletondi.telldontaskkata.domain.exception;

public class OrderApprovalException extends RuntimeException {

    public OrderApprovalException() {
        super();
    }

    public OrderApprovalException(String message) {
        super(message);
    }

    public OrderApprovalException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderApprovalException(Throwable cause) {
        super(cause);
    }

    protected OrderApprovalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
