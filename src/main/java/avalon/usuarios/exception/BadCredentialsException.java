package avalon.usuarios.exception;

import org.springframework.http.HttpStatus;

/**
 * Thrown if an authentication request is rejected because the credentials are
 * invalid.
 * For this exception to be thrown, it means the account is neither locked nor
 * disabled.
 *
 * @author Pablo Torres
 */
public class BadCredentialsException extends CustomException {

    /**
     * Constructs a <code>BadCredentialsException</code> with the specified message.
     * 
     * @param msg the detail message
     */
    public BadCredentialsException(String message, String className, boolean toLog) {
        super(HttpStatus.FORBIDDEN, message, className, toLog);
    }

    public BadCredentialsException(String message, String className, boolean toLog, String messageToLog) {
        super(HttpStatus.FORBIDDEN, message, className, toLog, messageToLog);
    }

}
