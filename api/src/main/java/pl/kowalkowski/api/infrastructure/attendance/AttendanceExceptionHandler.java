package pl.kowalkowski.api.infrastructure.attendance;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class AttendanceExceptionHandler {

    @ExceptionHandler(AttendanceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public AttendanceResponse handleAttendanceException(AttendanceException ex) {
        return new AttendanceResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public AttendanceResponse handleAttendanceDataIntegrityViolationException(DataIntegrityViolationException e) {

        if (e.getMessage().contains("unique_attendance_entry")) {
            return new AttendanceResponse("ATTENDANCE IS ALREADY CREATED", HttpStatus.CONFLICT, null);
        }
        if (e.getMessage().contains("entry_exit_dates")) {
            return new AttendanceResponse("ENTRY DATE CANNOT AFTER PAST EXIT DATE ", HttpStatus.CONFLICT, null);
        } else {
            return new AttendanceResponse("UNIQUE KEY VIOLATION", HttpStatus.CONFLICT, null);
        }
    }


}
