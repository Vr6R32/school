package pl.kowalkowski.api.infrastructure.parent;

import org.springframework.http.HttpStatus;

public record ParentResponse(String message, HttpStatus status, ParentDTO parent) {
}
