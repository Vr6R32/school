package pl.kowalkowski.api.facade;

import lombok.RequiredArgsConstructor;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceService;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;

@RequiredArgsConstructor
public class AttendanceFacade {

    private final AttendanceService attendanceService;

    public AttendanceResponse createNewAttendance(NewAttendanceRequest request){
        return attendanceService.createNewAttendance(request);
    }

}
