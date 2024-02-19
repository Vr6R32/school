package pl.kowalkowski.api.infrastructure.invoice;

import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceResponse;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.time.Year;
import java.util.UUID;

public interface InvoiceService {

    InvoiceResponse<InvoiceSchoolDTO> getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month integerMonth, Year year);

    InvoiceResponse<InvoiceParentDTO> getInvoiceForParentByIdAndPeriod(UUID parentId, Month integerMonth, Year year);


}
