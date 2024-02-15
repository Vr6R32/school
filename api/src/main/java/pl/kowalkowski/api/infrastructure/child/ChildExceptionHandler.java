package pl.kowalkowski.api.infrastructure.child;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ChildExceptionHandler {

    @ExceptionHandler(ChildException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ChildResponse handleChildException(ChildException ex) {
        return new ChildResponse(ex.getMessage(),HttpStatus.BAD_REQUEST, null);
    }
}