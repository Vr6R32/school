package pl.kowalkowski.api.infrastructure.invoice.model;

import org.springframework.http.HttpStatus;

public record InvoiceResponse<T extends InvoiceDTO>(String message, HttpStatus status, T invoice) {
}