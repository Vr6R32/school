package pl.kowalkowski.api.infrastructure.attendance;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.Attendance;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.persistance.AttendanceRepository;

import static pl.kowalkowski.api.infrastructure.attendance.AttendanceMapper.mapAttendanceToDTO;

@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {


    private final AttendanceRepository attendanceRepository;
    private final ChildFacade childFacade;


    @Override
    public AttendanceResponse createNewAttendance(NewAttendanceRequest request) {

        Child child = childFacade.getChildById(request.childId());

        Attendance newAttendance = Attendance.builder()
                .child(child)
                .entryDate(request.entryDate())
                .exitDate(request.exitDate())
                .build();

        attendanceRepository.save(newAttendance);

        return new AttendanceResponse("ATTENDANCE CREATED", HttpStatus.OK, mapAttendanceToDTO(newAttendance));
    }
}
