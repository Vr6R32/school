package pl.kowalkowski.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kowalkowski.api.facade.InvoiceFacade;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/invoices")
class InvoiceController implements InvoiceApi {

    private final InvoiceFacade invoiceFacade;

    @Override
    @GetMapping("school")
    public InvoiceResponse<InvoiceSchoolDTO> getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month month, int year) {
        return invoiceFacade.getInvoiceForSchoolByIdAndPeriod(schoolId, month, year);
    }

    @Override
    @GetMapping("parent")
    public InvoiceResponse<InvoiceParentDTO> getInvoiceForParentByIdAndPeriod(UUID parentId, Month month, int year) {
        return invoiceFacade.getInvoiceForParentByIdAndPeriod(parentId, month, year);
    }
}