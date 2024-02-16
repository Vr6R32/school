package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import java.math.BigDecimal;
import java.util.List;

@Builder
public record SummaryChildPayments(List<InvoiceChildSummaryDTO> childrenPayments, BigDecimal totalPayment) {
}