package pl.kowalkowski.api.infrastructure.attendance;


import java.util.List;
import java.util.UUID;

public interface AttendanceService {

    AttendanceResponse createNewAttendance(NewAttendanceRequest request);

    List<AttendanceDTO> getAttendancesForSchoolByIdAndPeriod(UUID schoolId, int month, int year);

    List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(UUID parentId, int month, int year);
}
