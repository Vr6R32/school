package pl.kowalkowski.api.infrastructure.invoice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;

@RestControllerAdvice
class InvoiceExceptionHandler {

    @ExceptionHandler(InvoiceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public InvoiceResponse<InvoiceDTO> handleParentException(InvoiceException ex) {
        return new InvoiceResponse<>(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}