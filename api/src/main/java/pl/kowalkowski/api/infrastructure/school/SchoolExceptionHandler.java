package pl.kowalkowski.api.infrastructure.school;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class SchoolExceptionHandler {

    @ExceptionHandler(SchoolException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public SchoolResponse handleSchoolException(SchoolException ex) {
        return new SchoolResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}