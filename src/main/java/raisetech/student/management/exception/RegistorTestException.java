package raisetech.student.management.exception;

public class RegistorTestException extends RuntimeException {
    public RegistorTestException() {
        super();
    }

    public RegistorTestException(String message) {
        super(message);
    }

    public RegistorTestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistorTestException(Throwable cause) {
        super(cause);
    }

    protected RegistorTestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
