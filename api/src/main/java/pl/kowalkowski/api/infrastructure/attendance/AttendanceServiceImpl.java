package pl.kowalkowski.api.infrastructure.attendance;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.Attendance;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.parent.ParentException;
import pl.kowalkowski.api.infrastructure.school.SchoolException;
import pl.kowalkowski.api.persistance.AttendanceRepository;

import java.util.List;
import java.util.UUID;

import static pl.kowalkowski.api.infrastructure.attendance.AttendanceMapper.mapAttendanceToDTO;

@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {


    private final AttendanceRepository attendanceRepository;
    private final ChildFacade childFacade;
    private final SchoolFacade schoolFacade;
    private final ParentFacade parentFacade;


    @Override
    public AttendanceResponse createNewAttendance(NewAttendanceRequest request) {

        Child child = childFacade.getChildEntityById(request.childId());

        Attendance newAttendance = Attendance.builder()
                .child(child)
                .entryDate(request.entryDate())
                .exitDate(request.exitDate())
                .build();

        attendanceRepository.save(newAttendance);

        return new AttendanceResponse("ATTENDANCE CREATED", HttpStatus.OK, mapAttendanceToDTO(newAttendance));
    }

    @Override
    public List<AttendanceDTO> getAttendancesForSchoolByIdAndPeriod(UUID schoolId, int month, int year) {
        List<Attendance> attendances = attendanceRepository.findAttendancesBySchoolIdAndMonth(schoolId, month, year);

        // Just some extra validation , its not really needed if not wanted, it depends
        if (!schoolFacade.checkSchoolExistsById(schoolId)) {
            throw new SchoolException("SCHOOL WITH ID " + "[" + schoolId + "]" + " DOESNT EXISTS");
        }

        if (attendances.isEmpty()) {
            throw new AttendanceException("THERE ARE NO FOR PERIOD" +"[" + month +", "+ year + "]"+ "ATTENDANCES");
        }

        return attendances.stream()
                .map(AttendanceMapper::mapAttendanceToDTO)
                .toList();
    }

    @Override
    public List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(UUID parentId, int month, int year) {

        // Just some extra validation , its not really needed if not wanted, it depends
        if (!parentFacade.checkParentExistsById(parentId)) {
            throw new ParentException("PARENT WITH ID " + "[" + parentId + "]" + " DOESNT EXISTS");
        }

        List<Attendance> attendances = attendanceRepository.findAttendancesByParentIdAndMonth(parentId, month, year);

        if (attendances.isEmpty()) {
            throw new AttendanceException("THERE ARE NO FOR PERIOD" +"[" + month +", "+ year + "]"+ "ATTENDANCES");
        }

        return attendances.stream()
                .map(AttendanceMapper::mapAttendanceToDTO)
                .toList();

    }
}
