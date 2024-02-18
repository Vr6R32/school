package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Builder
public record InvoiceParentDTO(Set<ParentDTO> parents, BigDecimal totalPayment, List<InvoiceChildSummaryDTO> childrenPayments) implements InvoiceDTO  {
}
