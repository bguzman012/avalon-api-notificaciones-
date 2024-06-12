package avalon.usuarios.exception.Handlers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import avalon.usuarios.exception.CustomException;
import avalon.usuarios.exception.ErrorResponseCustom;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(CustomExceptionHandler.class.getName());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        String mensajeError = "Ocurrió un error interno en el servidor.";
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("error", mensajeError));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseCustom> handleException(CustomException e,
            HttpServletRequest request) {

        if (e.getToLog()) {
            LOGGER.severe(
                    "LOG (Error): Class " + e.getClassName() + " -> " + e.getMessage());
        }

        return ResponseEntity.status(e.getStatusCode()).body(
                new ErrorResponseCustom(
                        LocalDateTime.now(),
                        e.getStatusCode(), e.getMessage(),
                        request.getRequestURI()));
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponseCustom errorResponse = new ErrorResponseCustom(
                LocalDateTime.now(),
                ex.getStatusCode(),
                ex.getBody().getDetail(),
                request.getRequestURI(),
                errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
