package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record InvoiceSchoolDTO(SchoolDTO school, BigDecimal totalPayment, List<InvoiceChildSummaryDTO> childrenPayments) {
}