package cz.cvut.fit.tjv.fuelapp.controler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { IllegalAccessError.class })
    protected ResponseEntity<Object> handleIllegalArgument(
            RuntimeException ex,
            WebRequest request
    )
    {
        String body = "Supplied argument was illegal";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class })
    protected ResponseEntity<Object> handleNonExistingEntity(
            RuntimeException ex,
            WebRequest request
    )
    {
        String body = "Supplied entity does not exist";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
