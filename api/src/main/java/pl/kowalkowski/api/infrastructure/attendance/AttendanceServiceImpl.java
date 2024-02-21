package pl.kowalkowski.api.infrastructure.attendance;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.Attendance;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.invoice.model.ClientType;
import pl.kowalkowski.api.infrastructure.parent.ParentException;
import pl.kowalkowski.api.infrastructure.school.SchoolException;
import pl.kowalkowski.api.persistance.AttendanceRepository;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.UUID;

import static pl.kowalkowski.api.infrastructure.attendance.AttendanceMapper.mapAttendanceToDTO;

@Slf4j
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

    public List<AttendanceDTO> getClientAttendances(UUID clientId, Month month, Year year, ClientType clientType) {
            switch (clientType) {
                case SCHOOL -> {
                    validateSchoolExists(clientId);
                    List<Attendance> schoolAttendances = attendanceRepository.findAttendancesBySchoolIdAndMonth(clientId, month.getValue(), year.getValue());
                    validateAttendanceListNotEmpty(schoolAttendances, month, year);
                    return schoolAttendances.stream()
                            .map(AttendanceMapper::mapAttendanceToDTO)
                            .toList();
                }
                case PARENT -> {
                    validateParentExists(clientId);
                    List<Attendance> parentAttendances = attendanceRepository.findAttendancesByParentIdAndMonth(clientId, month.getValue(), year.getValue());
                    validateAttendanceListNotEmpty(parentAttendances, month, year);
                    return parentAttendances.stream()
                            .map(AttendanceMapper::mapAttendanceToDTO)
                            .toList();
                }
                default -> {
                    log.info("[ATTENDANCE-SERVICE] -> REQUESTED ATTENDANCE WITH UNSUPPORTED CLIENT TYPE [{}] FOR ID [{}] FOR PERIOD -> YEAR: [{}] MONTH: [{}]", clientType, clientId, year, month);
                    throw new AttendanceException("UNSUPPORTED CLIENT TYPE " + clientType);
                }
            }
    }

    private void validateSchoolExists(UUID schoolId) {
        if (!schoolFacade.checkSchoolExistsById(schoolId)) {
            throw new SchoolException("SCHOOL WITH ID " + "[" + schoolId + "]" + " DOESN'T EXIST");
        }
    }

    private void validateParentExists(UUID parentId) {
        if (!parentFacade.checkParentExistsById(parentId)) {
            throw new ParentException("PARENT WITH ID " + "[" + parentId + "]" + " DOESN'T EXIST");
        }
    }

    private void validateAttendanceListNotEmpty(List<Attendance> attendances, Month month, Year year) {
        if (attendances.isEmpty()) {
            throw new AttendanceException("THERE ARE NO ATTENDANCES FOR PERIOD" + "[" + month.getValue() + ", " + year.getValue() + "]");
        }
    }
}
