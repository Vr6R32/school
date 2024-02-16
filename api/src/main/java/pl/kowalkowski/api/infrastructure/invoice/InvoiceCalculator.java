package pl.kowalkowski.api.infrastructure.invoice;

import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.child.ChildDTO;
import pl.kowalkowski.api.infrastructure.invoice.model.*;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;
import pl.kowalkowski.api.infrastructure.parent.ParentException;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;
import pl.kowalkowski.api.infrastructure.school.SchoolException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvoiceCalculator {

    private static final LocalTime FREE_PERIOD_START = LocalTime.of(7, 0);
    private static final LocalTime FREE_PERIOD_END = LocalTime.of(12, 0);


    public InvoiceSchoolDTO calculateSchoolSummary(List<AttendanceDTO> attendanceList) {

        SchoolDTO school = attendanceList.stream()
                .findFirst()
                .map(attendanceDTO -> attendanceDTO.child().school())
                .orElseThrow(() -> new SchoolException("SCHOOL IS NOT PRESENT"));

        SummaryChildPayments payments = getSummaryPayments(attendanceList);

        return InvoiceSchoolDTO.builder()
                .school(school)
                .totalPayment(payments.totalPayment())
                .childrenPayments(payments.childrenPayments())
                .build();
    }

    public InvoiceParentDTO calculateParentSummary(List<AttendanceDTO> attendanceList) {

        ParentDTO parent = attendanceList.stream()
                .findFirst()
                .map(attendanceDTO -> attendanceDTO.child().parent())
                .orElseThrow(() -> new ParentException("PARENT IS NOT PRESENT"));

        SummaryChildPayments payments = getSummaryPayments(attendanceList);

        return InvoiceParentDTO.builder()
                .parent(parent)
                .totalPayment(payments.totalPayment())
                .childrenPayments(payments.childrenPayments())
                .build();
    }

    private SummaryChildPayments getSummaryPayments(List<AttendanceDTO> attendanceList) {
        Map<ChildDTO, List<AttendanceDTO>> attendancesByChild = attendanceList.stream()
                .collect(Collectors.groupingBy(AttendanceDTO::child));

        List<InvoiceChildSummaryDTO> childrenPayments = calculateChildrenPayments(attendancesByChild);

        BigDecimal totalPayment = childrenPayments.stream()
                .map(InvoiceChildSummaryDTO::totalPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new SummaryChildPayments(childrenPayments, totalPayment);
    }



    private List<InvoiceChildSummaryDTO> calculateChildrenPayments(Map<ChildDTO, List<AttendanceDTO>> attendancesByChild) {
        return attendancesByChild.entrySet().stream()
                .map(entry -> {
                    ChildDTO child = entry.getKey();
                    List<AttendanceDTO> childAttendances = entry.getValue();

                    SummaryTotalPaymentHours totalPaymentForChild = calculateTotalPayment(childAttendances);
                    int hoursSpent = calculateTotalHoursSpent(childAttendances);

                    return InvoiceChildSummaryDTO.builder()
                            .child(child)
                            .totalPayment(totalPaymentForChild.totalPayment())
                            .hoursSpent(hoursSpent)
                            .payHours(totalPaymentForChild.totalBillableHours())
                            .build();

                })
                .toList();
    }


    private int calculateTotalHoursSpent(List<AttendanceDTO> attendanceList) {
        return attendanceList.stream()
                .mapToInt(this::hoursSpent)
                .sum();
    }

    private SummaryTotalPaymentHours calculateTotalPayment(List<AttendanceDTO> attendanceList) {
        long totalBillableHours = attendanceList.stream()
                .mapToLong(attendance -> calculateBillableHours(
                        attendance.entryDate().toLocalTime(),
                        attendance.exitDate().toLocalTime()))
                .sum();

        BigDecimal totalPayment = attendanceList.stream()
                .map(this::calculateTotalPayment)
                .map(SummaryTotalPaymentHours::totalPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SummaryTotalPaymentHours(totalPayment, totalBillableHours);
    }

    private SummaryTotalPaymentHours calculateTotalPayment(AttendanceDTO attendance) {
        long billableHours = calculateBillableHours(
                attendance.entryDate().toLocalTime(),
                attendance.exitDate().toLocalTime());
        BigDecimal hourPrice = attendance.child().school().hourPrice();
        BigDecimal totalPayment = hourPrice.multiply(BigDecimal.valueOf(billableHours)).setScale(2, RoundingMode.HALF_UP);

        return new SummaryTotalPaymentHours(totalPayment, billableHours);
    }

    private int hoursSpent(AttendanceDTO attendance) {
        long minutesSpent = attendance.entryDate().until(attendance.exitDate(), ChronoUnit.MINUTES);
        return (int) Math.ceil(minutesSpent / 60.0);
    }

    private long calculateBillableHours(LocalTime entryTime, LocalTime exitTime) {
        if (entryTime.isBefore(FREE_PERIOD_START) && exitTime.isBefore(FREE_PERIOD_END)) {
            return getMorningPayHours(entryTime);
        }

        if (entryTime.isBefore(FREE_PERIOD_START) && exitTime.isAfter(FREE_PERIOD_END)) {
            long morningPayMinutes = getMorningPayHours(entryTime);
            long afterNoonPayMinutes = getAfterNoonPayHours(exitTime);
            return morningPayMinutes + afterNoonPayMinutes;
        }

        if (entryTime.isAfter(FREE_PERIOD_START) && exitTime.isAfter(FREE_PERIOD_END)) {
            return getAfterNoonPayHours(exitTime);
        }

        return 0;
    }

    private long getAfterNoonPayHours(LocalTime exitTime) {
        return ceilMinutesToHours(ChronoUnit.MINUTES.between(FREE_PERIOD_END, exitTime));
    }

    private long getMorningPayHours(LocalTime entryTime) {
        return ceilMinutesToHours(ChronoUnit.MINUTES.between(entryTime, FREE_PERIOD_START));
    }

    private long ceilMinutesToHours(long minutes) {
        return (long) Math.ceil(minutes / 60.0);
    }
}