package pl.kowalkowski.api.facade;

import lombok.RequiredArgsConstructor;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceService;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

@RequiredArgsConstructor
public class InvoiceFacade {

    private final InvoiceService invoiceService;

    public InvoiceResponse<InvoiceSchoolDTO> getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month month, Year year) {
        return invoiceService.getInvoiceForSchoolByIdAndPeriod(schoolId, month, year);
    }

    public InvoiceResponse<InvoiceParentDTO> getInvoiceForParentByIdAndPeriod(UUID parentId, Month month, Year year) {
        return invoiceService.getInvoiceForParentByIdAndPeriod(parentId, month, year);
    }

}
