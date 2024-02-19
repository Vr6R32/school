package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Set;

@Builder
public record InvoiceParentDTO(Month month, Year year, Set<ParentDTO> parents,BigDecimal totalPayment, List<InvoiceChildSummaryDTO> childrenPayments) implements InvoiceDTO  {
}
