package pl.kowalkowski.api.infrastructure.attendance;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.Attendance;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.infrastructure.invoice.InvoiceCalculator;
import pl.kowalkowski.api.persistance.AttendanceRepository;

import java.util.List;
import java.util.UUID;

import static pl.kowalkowski.api.infrastructure.attendance.AttendanceMapper.mapAttendanceToDTO;

@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {


    private final AttendanceRepository attendanceRepository;
    private final ChildFacade childFacade;
    private final InvoiceCalculator invoiceCalculator;


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
        return attendanceRepository
                .findAttendancesBySchoolIdAndMonth(schoolId, month, year)
                .stream()
                .map(AttendanceMapper::mapAttendanceToDTO)
                .toList();
    }

    @Override
    public List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(UUID parentId, int month, int year) {

        return attendanceRepository
                .findAttendancesByParentIdAndMonth(parentId, month, year)
                .stream()
                .map(AttendanceMapper::mapAttendanceToDTO)
                .toList();

    }
}
