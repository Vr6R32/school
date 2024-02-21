package pl.kowalkowski.api.infrastructure.invoice;

public class InvoiceException extends RuntimeException {
    public InvoiceException(String message) {
        super(message);
    }
}