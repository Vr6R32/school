package pl.kowalkowski.api.infrastructure;

import org.springframework.http.HttpStatus;

public record GlobalExceptionResponse(String message, HttpStatus status){


}
