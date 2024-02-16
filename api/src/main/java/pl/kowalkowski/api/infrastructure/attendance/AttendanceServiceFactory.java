package pl.kowalkowski.api.infrastructure.attendance;

import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceCalculator;
import pl.kowalkowski.api.persistance.AttendanceRepository;


public class AttendanceServiceFactory {

    private AttendanceServiceFactory() {
    }

    public static AttendanceService createAttendanceService(AttendanceRepository attendanceRepository, ChildFacade childFacade, InvoiceCalculator invoiceCalculator) {
        return new AttendanceServiceImpl(attendanceRepository,childFacade, invoiceCalculator);
    }

}
