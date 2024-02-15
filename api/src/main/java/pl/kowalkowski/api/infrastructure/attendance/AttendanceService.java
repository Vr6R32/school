package pl.kowalkowski.api.infrastructure.attendance;

public interface AttendanceService {

    AttendanceResponse createNewAttendance(NewAttendanceRequest request);

}
