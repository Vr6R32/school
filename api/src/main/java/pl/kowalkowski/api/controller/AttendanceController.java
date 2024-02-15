package pl.kowalkowski.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kowalkowski.api.facade.AttendanceFacade;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/attendances")
public class AttendanceController {

    private final AttendanceFacade attendanceFacade;

    @PostMapping
    AttendanceResponse createNewAttendance(@Valid @RequestBody NewAttendanceRequest request){
        return attendanceFacade.createNewAttendance(request);
    }

}
