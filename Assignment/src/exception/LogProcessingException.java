package exception;

public class LogProcessingException extends RuntimeException {
    public LogProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
