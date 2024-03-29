package pl.kowalkowski.api.infrastructure.parent;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ParentExceptionHandler {

    @ExceptionHandler(ParentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ParentResponse handleParentException(ParentException ex) {
        return new ParentResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}