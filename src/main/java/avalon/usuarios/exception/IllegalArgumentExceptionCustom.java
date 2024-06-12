package avalon.usuarios.exception;

import org.springframework.http.HttpStatus;

public class IllegalArgumentExceptionCustom extends CustomException {

    public IllegalArgumentExceptionCustom(String message, String className, boolean toLog) {
        super(HttpStatus.BAD_REQUEST, message, className, toLog);
    }
}
