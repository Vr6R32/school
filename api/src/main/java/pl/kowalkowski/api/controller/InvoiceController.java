package pl.kowalkowski.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kowalkowski.api.facade.InvoiceFacade;
import pl.kowalkowski.api.infrastructure.invoice.model.*;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/invoices")
class InvoiceController implements InvoiceApi {

    private final InvoiceFacade invoiceFacade;

    @GetMapping
    public InvoiceResponse<InvoiceDTO> generateClientSummaryInvoice(UUID clientId, Month month, Year year, ClientType clientType) {
        return invoiceFacade.generateClientSummaryInvoice(clientId, month, year, clientType);
    }

}