package pl.kowalkowski.api.infrastructure.child;

import org.springframework.http.HttpStatus;

public record ChildResponse(String message, HttpStatus status, ChildDTO child) {
}
