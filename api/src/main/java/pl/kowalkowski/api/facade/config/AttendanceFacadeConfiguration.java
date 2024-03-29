package pl.kowalkowski.api.facade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceService;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceServiceFactory;
import pl.kowalkowski.api.persistance.AttendanceRepository;

@Configuration
@RequiredArgsConstructor
class AttendanceFacadeConfiguration {

    private final AttendanceRepository attendanceRepository;
    private final ChildFacade childFacade;
    private final SchoolFacade schoolFacade;
    private final ParentFacade parentFacade;

    @Bean
    AttendanceFacade attendanceFacade() {
        AttendanceService attendanceService = AttendanceServiceFactory.createAttendanceService(attendanceRepository, childFacade, schoolFacade, parentFacade);
        return new AttendanceFacade(attendanceService);
    }

}
