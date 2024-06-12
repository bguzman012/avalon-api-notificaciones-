package avalon.usuarios.exception;

import org.springframework.http.HttpStatus;

public class FirebaseMessagingFailureException extends CustomException {
    public FirebaseMessagingFailureException(String message, String className, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, className, true, cause.getMessage());
    }
}