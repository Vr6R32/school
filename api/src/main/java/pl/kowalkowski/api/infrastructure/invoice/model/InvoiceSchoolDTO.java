package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Builder
public record InvoiceSchoolDTO(Month month, Year year, SchoolDTO school, BigDecimal totalPayment, List<InvoiceChildSummaryDTO> childrenPayments) implements InvoiceDTO {
}