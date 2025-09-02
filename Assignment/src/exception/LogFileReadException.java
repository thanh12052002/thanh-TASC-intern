package exception;

public class LogFileReadException extends LogProcessingException {
    public LogFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
