package pl.kowalkowski.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendances")
class AttendanceController implements AttendanceApi {

    private final AttendanceFacade attendanceFacade;

    @PostMapping
    public AttendanceResponse createNewAttendance(@Valid @RequestBody NewAttendanceRequest request) {
        return attendanceFacade.createNewAttendance(request);
    }

    @GetMapping("school")
    public List<AttendanceDTO> getAttendancesForSchoolByIdAndPeriod(UUID schoolId, Month month, Year year) {
        return attendanceFacade.getAttendancesForSchoolByIdAndPeriod(schoolId, month, year);
    }

    @GetMapping("parent")
    public List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(UUID parentId, Month month, Year year) {
        return attendanceFacade.getAttendancesForParentByIdAndPeriod(parentId, month, year);
    }
}
