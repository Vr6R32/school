package pl.kowalkowski.api.infrastructure.attendance;


import pl.kowalkowski.api.infrastructure.invoice.model.ClientType;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.UUID;

public interface AttendanceService {

    AttendanceResponse createNewAttendance(NewAttendanceRequest request);

    List<AttendanceDTO> getClientAttendances(UUID clientId, Month month, Year year , ClientType clientType);

}
