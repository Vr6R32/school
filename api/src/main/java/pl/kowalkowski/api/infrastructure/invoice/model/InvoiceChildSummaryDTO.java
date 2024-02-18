package pl.kowalkowski.api.infrastructure.invoice.model;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceNoRelationDTO;
import pl.kowalkowski.api.infrastructure.child.ChildDTO;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record InvoiceChildSummaryDTO(ChildDTO child, BigDecimal totalPayment, int totalMinutesSpent, long payHours, int attendancesCount, List<AttendanceNoRelationDTO> attendances) {
}
