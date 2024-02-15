package pl.kowalkowski.api.infrastructure.attendance;

import org.springframework.http.HttpStatus;

public record AttendanceResponse(String message, HttpStatus status, AttendanceDTO attendance) {
}
