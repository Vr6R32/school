package pl.kowalkowski.api.facade;

import lombok.RequiredArgsConstructor;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceService;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class AttendanceFacade {

    private final AttendanceService attendanceService;

    public AttendanceResponse createNewAttendance(NewAttendanceRequest request){
        return attendanceService.createNewAttendance(request);
    }

    public List<AttendanceDTO> getAttendancesForSchoolByIdAndPeriod(UUID schoolId, Month month, Year year) {
        int integerMonth = month.getValue();
        int integerYear = year.getValue();
        return attendanceService.getAttendancesForSchoolByIdAndPeriod(schoolId, integerMonth, integerYear);
    }

    public List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(UUID parentId, Month month, Year year) {
        int integerMonth = month.getValue();
        int integerYear = year.getValue();
        return attendanceService.getAttendancesForParentByIdAndPeriod(parentId, integerMonth, integerYear);
    }
}
