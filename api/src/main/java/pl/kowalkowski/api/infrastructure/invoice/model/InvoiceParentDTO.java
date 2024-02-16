package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record InvoiceParentDTO(ParentDTO parent, BigDecimal totalPayment, List<InvoiceChildSummaryDTO> childrenPayments) {
}