package pl.kowalkowski.api.facade;

import lombok.RequiredArgsConstructor;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceService;
import pl.kowalkowski.api.infrastructure.invoice.model.ClientType;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

@RequiredArgsConstructor
public class InvoiceFacade {

    private final InvoiceService invoiceService;

    public InvoiceResponse<InvoiceDTO> generateClientSummaryInvoice(UUID clientId, Month month, Year year, ClientType clientType) {
        return invoiceService.generateClientSummaryInvoice(clientId, month, year, clientType);
    }

}
