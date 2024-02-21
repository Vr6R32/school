package pl.kowalkowski.api.infrastructure;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        String stringifyErrors = String.join(", ", errors.values());
        return new GlobalExceptionResponse(stringifyErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse handleDateParseException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof JsonMappingException jsonMappingException && (jsonMappingException.getMessage().contains("LocalDate"))) {
            return new GlobalExceptionResponse("INVALID DATE FORMAT", HttpStatus.BAD_REQUEST);
        }
        return new GlobalExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionResponse handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if(ex.getMessage().contains("ClientType")){
            return new GlobalExceptionResponse("UNSUPPORTED CLIENT TYPE", HttpStatus.BAD_REQUEST);
        }
        return new GlobalExceptionResponse("INVALID ARGUMENT " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
