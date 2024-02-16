package pl.kowalkowski.api.infrastructure.invoice;

import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceParentDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.InvoiceSchoolDTO;

import java.time.Month;
import java.util.UUID;

public interface InvoiceService {

    InvoiceSchoolDTO getInvoiceForSchoolByIdAndPeriod(UUID schoolId, Month integerMonth, int year);

    InvoiceParentDTO getInvoiceForParentByIdAndPeriod(UUID parentId, Month integerMonth, int year);


}
