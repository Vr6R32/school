package pl.kowalkowski.api.infrastructure.invoice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.child.ChildDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class InvoiceCalculatorTest {

    @ParameterizedTest
    @MethodSource("provide_Test_Data_For_Morning_Payment")
    void should_bill_morning_hours(LocalTime entryTime, LocalTime exitTime, long expectedBillableHours) {
        //given
        InvoiceCalculator invoiceCalculator = spy(new InvoiceCalculator());

        //when
        long billableHours = invoiceCalculator.calculateBillableHours(entryTime, exitTime);

        //then
        assertEquals(expectedBillableHours, billableHours);

        verify(invoiceCalculator, times(1)).getMorningPayHours(any());
        verify(invoiceCalculator, times(0)).getAfterNoonPayHours(any());
    }

    @ParameterizedTest
    @MethodSource("provide_Test_Data_For_Afternoon_Payment")
    void should_bill_afternoon_hours(LocalTime entryTime, LocalTime exitTime, long expectedBillableHours) {
        //given
        InvoiceCalculator invoiceCalculator = spy(new InvoiceCalculator());

        //when
        long billableHours = invoiceCalculator.calculateBillableHours(entryTime, exitTime);

        //then
        assertEquals(expectedBillableHours, billableHours);

        verify(invoiceCalculator, times(0)).getMorningPayHours(any());
        verify(invoiceCalculator, times(1)).getAfterNoonPayHours(any());
    }

    @ParameterizedTest
    @MethodSource("provide_Test_Data_For_Both_Morning_And_Afternoon_Payment")
    void should_bill_morning_and_afternoon_hours(LocalTime entryTime, LocalTime exitTime, long expectedBillableHours) {
        //given
        InvoiceCalculator invoiceCalculator = spy(new InvoiceCalculator());

        //when
        long billableHours = invoiceCalculator.calculateBillableHours(entryTime, exitTime);

        //then
        assertEquals(expectedBillableHours, billableHours);

        verify(invoiceCalculator, times(1)).getMorningPayHours(any());
        verify(invoiceCalculator, times(1)).getAfterNoonPayHours(any());
    }

    @ParameterizedTest
    @MethodSource("provide_Test_Data_For_Free_Period")
    void should_not_bill_free_period(LocalTime entryTime, LocalTime exitTime) {
        //given
        InvoiceCalculator invoiceCalculator = spy(new InvoiceCalculator());

        //when
        long billableHours = invoiceCalculator.calculateBillableHours(entryTime, exitTime);

        //then
        assertEquals(0, billableHours);

        verify(invoiceCalculator, times(0)).getMorningPayHours(any());
        verify(invoiceCalculator, times(0)).getAfterNoonPayHours(any());
    }

    private static Stream<Arguments> provide_Test_Data_For_Morning_Payment() {
        return Stream.of(
                Arguments.of(LocalTime.of(6, 24), LocalTime.of(10, 32), 1),
                Arguments.of(LocalTime.of(5, 59), LocalTime.of(11, 59), 2),
                Arguments.of(LocalTime.of(6, 58), LocalTime.of(12, 0), 1),
                Arguments.of(LocalTime.of(6, 0), LocalTime.of(12, 0), 1)
        );
    }

    private static Stream<Arguments> provide_Test_Data_For_Afternoon_Payment() {
        return Stream.of(
                Arguments.of(LocalTime.of(12, 1), LocalTime.of(14, 59), 3),
                Arguments.of(LocalTime.of(13, 38), LocalTime.of(16, 24), 5),
                Arguments.of(LocalTime.of(12, 0), LocalTime.of(12, 3), 1),
                Arguments.of(LocalTime.of(12, 0), LocalTime.of(13, 0), 1)
        );
    }

    private static Stream<Arguments> provide_Test_Data_For_Both_Morning_And_Afternoon_Payment() {
        return Stream.of(
                Arguments.of(LocalTime.of(6, 59), LocalTime.of(12, 1), 2),
                Arguments.of(LocalTime.of(6, 0), LocalTime.of(13, 54), 3),
                Arguments.of(LocalTime.of(5, 59), LocalTime.of(13, 14), 4),
                Arguments.of(LocalTime.of(6, 1), LocalTime.of(14, 35), 4)
        );
    }

    private static Stream<Arguments> provide_Test_Data_For_Free_Period() {
        return Stream.of(
                Arguments.of(LocalTime.of(7, 1), LocalTime.of(12, 0)),
                Arguments.of(LocalTime.of(7, 0), LocalTime.of(12, 0)),
                Arguments.of(LocalTime.of(7, 1), LocalTime.of(11, 59)),
                Arguments.of(LocalTime.of(7, 0), LocalTime.of(11, 59))
        );
    }

    @ParameterizedTest
    @MethodSource("provide_Test_Data_For_Attendance_List_Total_Minutes_Spent")
    void should_calculate_total_minutes_spent(List<AttendanceDTO> attendanceList, int expectedTotalMinutesSpent) {
        // given
        InvoiceCalculator invoiceCalculator = new InvoiceCalculator();

        // when
        int totalMinutesSpent = invoiceCalculator.calculateTotalMinutesSpent(attendanceList);

        // then
        assertEquals(expectedTotalMinutesSpent, totalMinutesSpent);
    }

    @ParameterizedTest
    @MethodSource("provide_Test_Data_For_Single_Attendance_Total_Minutes_Spent")
    void should_calculate_total_minutes_spent_for_single_attendance(AttendanceDTO attendance, int expectedTotalMinutesSpent) {
        // given
        InvoiceCalculator invoiceCalculator = new InvoiceCalculator();

        // when
        int totalMinutesSpent = invoiceCalculator.totalMinutesSpent(attendance);

        // then
        assertEquals(expectedTotalMinutesSpent, totalMinutesSpent);
    }

    private static Stream<Arguments> provide_Test_Data_For_Attendance_List_Total_Minutes_Spent() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        createAttendance(LocalTime.of(8, 0), LocalTime.of(12, 0)),
                        createAttendance(LocalTime.of(13, 0), LocalTime.of(16, 30))
                ), 450),
                Arguments.of(Arrays.asList(
                        createAttendance(LocalTime.of(9, 0), LocalTime.of(11, 0)),
                        createAttendance(LocalTime.of(14, 0), LocalTime.of(16, 0))
                ), 240),
                Arguments.of(Arrays.asList(
                        createAttendance(LocalTime.of(8, 30), LocalTime.of(12, 45)),
                        createAttendance(LocalTime.of(13, 15), LocalTime.of(15, 45)),
                        createAttendance(LocalTime.of(16, 0), LocalTime.of(17, 30))
                ), 495),
                Arguments.of(Arrays.asList(
                        createAttendance(LocalTime.of(10, 30), LocalTime.of(13, 45)),
                        createAttendance(LocalTime.of(14, 30), LocalTime.of(17, 0)),
                        createAttendance(LocalTime.of(18, 15), LocalTime.of(20, 45))
                ), 495)
        );
    }

    private static Stream<Arguments> provide_Test_Data_For_Single_Attendance_Total_Minutes_Spent() {
        return Stream.of(
                Arguments.of(createAttendance(LocalTime.of(8, 0), LocalTime.of(12, 0)), 240),
                Arguments.of(createAttendance(LocalTime.of(9, 0), LocalTime.of(11, 0)), 120),
                Arguments.of(createAttendance(LocalTime.of(8, 30), LocalTime.of(12, 45)), 255),
                Arguments.of(createAttendance(LocalTime.of(10, 30), LocalTime.of(13, 45)), 195)
        );
    }

    private static AttendanceDTO createAttendance(LocalTime entryTime, LocalTime exitTime) {
        UUID attendanceId = UUID.randomUUID();
        ChildDTO child = mock(ChildDTO.class);
        LocalDateTime entryDateTime = LocalDateTime.of(LocalDate.now(), entryTime);
        LocalDateTime exitDateTime = LocalDateTime.of(LocalDate.now(), exitTime);

        return new AttendanceDTO(attendanceId, child, entryDateTime, exitDateTime);
    }

}