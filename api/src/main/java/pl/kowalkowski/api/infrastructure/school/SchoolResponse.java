package pl.kowalkowski.api.infrastructure.school;

import org.springframework.http.HttpStatus;

public record SchoolResponse(String message, HttpStatus status, SchoolDTO school) {
}
