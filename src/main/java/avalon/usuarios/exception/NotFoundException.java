package avalon.usuarios.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public NotFoundException(String message, String className, boolean toLog) {
        super(HttpStatus.NOT_FOUND, message, className, toLog);
    }
}
