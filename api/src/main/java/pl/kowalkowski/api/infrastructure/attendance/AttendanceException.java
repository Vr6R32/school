package pl.kowalkowski.api.infrastructure.attendance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

class AttendanceException extends RuntimeException {

    public AttendanceException(String message) {
        super(message);
    }

    @ExceptionHandler(AttendanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AttendanceResponse handleAttendanceException(AttendanceException ex) {
        return new AttendanceResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}
