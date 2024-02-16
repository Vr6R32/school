package pl.kowalkowski.api.infrastructure.invoice;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class InvoiceCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideTestDataForMorningPayment")
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
    @MethodSource("provideTestDataForAfternoonPayment")
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
    @MethodSource("provideTestDataForBothMorningAndAfternoonPayment")
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
    @MethodSource("provideTestDataForFreePeriod")
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

    private static Stream<Arguments> provideTestDataForMorningPayment() {
        return Stream.of(
                Arguments.of(LocalTime.of(6, 24), LocalTime.of(10, 32), 1),
                Arguments.of(LocalTime.of(5, 59), LocalTime.of(11, 59), 2),
                Arguments.of(LocalTime.of(6, 58), LocalTime.of(12, 0), 1),
                Arguments.of(LocalTime.of(6, 0), LocalTime.of(12, 0), 1)
        );
    }

    private static Stream<Arguments> provideTestDataForAfternoonPayment() {
        return Stream.of(
                Arguments.of(LocalTime.of(12, 1), LocalTime.of(14, 59), 3),
                Arguments.of(LocalTime.of(13, 38), LocalTime.of(16, 24), 5),
                Arguments.of(LocalTime.of(12, 0), LocalTime.of(12, 3), 1),
                Arguments.of(LocalTime.of(12, 0), LocalTime.of(13, 0), 1)
        );
    }

    private static Stream<Arguments> provideTestDataForBothMorningAndAfternoonPayment() {
        return Stream.of(
                Arguments.of(LocalTime.of(6, 59), LocalTime.of(12, 1), 2),
                Arguments.of(LocalTime.of(6, 0), LocalTime.of(13, 54), 3),
                Arguments.of(LocalTime.of(5, 59), LocalTime.of(13, 14), 4),
                Arguments.of(LocalTime.of(6, 1), LocalTime.of(14, 35), 4)
        );
    }

    private static Stream<Arguments> provideTestDataForFreePeriod() {
        return Stream.of(
                Arguments.of(LocalTime.of(7, 1), LocalTime.of(12, 0)),
                Arguments.of(LocalTime.of(7, 0), LocalTime.of(12, 0)),
                Arguments.of(LocalTime.of(7, 1), LocalTime.of(11, 59)),
                Arguments.of(LocalTime.of(7, 0), LocalTime.of(11, 59))
        );
    }
}