package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.child.ChildDTO;

import java.math.BigDecimal;

@Builder
public record InvoiceChildSummaryDTO(ChildDTO child, BigDecimal totalPayment, int totalMinutesSpent, long payHours, int attendancesCount) {
}
