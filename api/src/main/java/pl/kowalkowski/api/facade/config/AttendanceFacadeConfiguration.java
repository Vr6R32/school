package pl.kowalkowski.api.facade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceService;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceServiceFactory;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceCalculator;
import pl.kowalkowski.api.persistance.AttendanceRepository;

@Configuration
@RequiredArgsConstructor
public class AttendanceFacadeConfiguration {

    private final AttendanceRepository attendanceRepository;
    private final ChildFacade childFacade;

    @Bean
    AttendanceFacade attendanceFacade() {
        InvoiceCalculator invoiceCalculator = new InvoiceCalculator();
        AttendanceService attendanceService = AttendanceServiceFactory.createAttendanceService(attendanceRepository,childFacade, invoiceCalculator);
        return new AttendanceFacade(attendanceService);
    }

}
