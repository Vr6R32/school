package pl.kowalkowski.api.infrastructure.attendance;

import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.persistance.AttendanceRepository;


public class AttendanceServiceFactory {

    private AttendanceServiceFactory() {
    }

    public static AttendanceService createAttendanceService(AttendanceRepository attendanceRepository,
                                                            ChildFacade childFacade, SchoolFacade schoolFacade, ParentFacade parentFacade) {
        return new AttendanceServiceImpl(attendanceRepository, childFacade, schoolFacade, parentFacade);
    }

}
