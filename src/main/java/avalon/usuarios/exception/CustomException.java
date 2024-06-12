package avalon.usuarios.exception;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String message;
    private final String className;
    private final boolean toLog;
    private final String messageToLog;

    // Constructor con todos los parámetros
    public CustomException(HttpStatus httpStatus, String message, String className, boolean toLog,
            String messageToLog) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
        this.className = className;
        this.toLog = toLog;
        this.messageToLog = messageToLog;
    }

    public CustomException(HttpStatus httpStatus, String message, String className, boolean toLog) {
        this(httpStatus, message, className, toLog, null);

    }

    public HttpStatus getStatusCode() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getClassName() {
        return className;
    }

    public boolean getToLog() {
        return toLog;
    }

    public String messageToLog() {
        return messageToLog;
    }

}