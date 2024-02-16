package pl.kowalkowski.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;

import java.time.Month;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendances")
public class AttendanceController {

    private final AttendanceFacade attendanceFacade;

    @PostMapping
    AttendanceResponse createNewAttendance(@Valid @RequestBody NewAttendanceRequest request){
        return attendanceFacade.createNewAttendance(request);
    }

    @GetMapping("school")
    List<AttendanceDTO> getAttendancesForSchoolByIdAndPeriod(UUID schoolId, Month month, int year){
        return attendanceFacade.getAttendancesForSchoolByIdAndPeriod(schoolId,month,year);
    }

    @GetMapping("parent")
    List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(UUID parentId, Month month, int year){
        return attendanceFacade.getAttendancesForParentByIdAndPeriod(parentId,month,year);
    }

}
