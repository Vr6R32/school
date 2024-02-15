package pl.kowalkowski.api.infrastructure.attendance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.kowalkowski.api.infrastructure.child.ChildException;

public class AttendanceException extends RuntimeException {

    public AttendanceException(String message) {
        super(message);
    }

    @ExceptionHandler(ChildException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AttendanceResponse handleAttendanceException(ChildException ex) {
        return new AttendanceResponse(ex.getMessage(),HttpStatus.BAD_REQUEST, null);
    }
}
