package pl.kowalkowski.api.facade;

import lombok.RequiredArgsConstructor;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceService;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;
import pl.kowalkowski.api.infrastructure.invoice.model.ClientType;

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

    public List<AttendanceDTO> getClientAttendances(UUID clientId, Month month, Year year, ClientType clientType) {
        return attendanceService.getClientAttendances(clientId, month, year, clientType);
    }
}
