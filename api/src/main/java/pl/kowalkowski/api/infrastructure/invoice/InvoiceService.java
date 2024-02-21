package pl.kowalkowski.api.infrastructure.invoice;

import pl.kowalkowski.api.infrastructure.invoice.model.ClientType;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;


import java.time.Month;
import java.time.Year;
import java.util.UUID;

public interface InvoiceService {

    InvoiceResponse<InvoiceDTO> generateClientSummaryInvoice(UUID clientId, Month month, Year year, ClientType clientType);

}
