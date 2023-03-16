package ua.com.alevel.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.exception.UnprocessableEntityException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({UnprocessableEntityException.class})
    public ResponseEntity<String> handleUnprocessableEntity(UnprocessableEntityException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exception.getMessage());
    }
}
