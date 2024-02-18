package pl.kowalkowski.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceDTO;
import pl.kowalkowski.api.infrastructure.attendance.AttendanceResponse;
import pl.kowalkowski.api.infrastructure.attendance.NewAttendanceRequest;

import java.time.Month;
import java.util.List;
import java.util.UUID;

interface AttendanceApi {

    @Operation(summary = "Create New Attendance",
            description = "Creates a new attendance record based on the provided request.")
    @ApiResponse(responseCode = "200", description = "Successful creation of attendance record")
    @ApiResponse(responseCode = "400", description = "Invalid request or validation error")
    @PostMapping
    AttendanceResponse createNewAttendance(@Valid @RequestBody NewAttendanceRequest request);

    @Operation(summary = "Get Attendances for School by ID and Period",
            description = "Returns a list of attendance records for a school based on provided ID, month, and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of attendances for specified school"),
            @ApiResponse(responseCode = "400", description = "School not found"),
            @ApiResponse(responseCode = "400", description = "Attendances not found"),
    })
    @GetMapping("school")
    List<AttendanceDTO> getAttendancesForSchoolByIdAndPeriod(
            @Parameter(description = "School ID", required = true) @RequestParam UUID schoolId,
            @Parameter(description = "Month", required = true) @RequestParam Month month,
            @Parameter(description = "Year", required = true) @RequestParam int year);

    @Operation(summary = "Get Attendances for Parent by ID and Period",
            description = "Returns a list of attendance records for a parent based on provided ID, month, and year.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of attendances for specified parent"),
            @ApiResponse(responseCode = "400", description = "Parent not found"),
            @ApiResponse(responseCode = "400", description = "Attendances not found"),
    })
    @GetMapping("parent")
    List<AttendanceDTO> getAttendancesForParentByIdAndPeriod(
            @Parameter(description = "Parent ID", required = true) @RequestParam UUID parentId,
            @Parameter(description = "Month", required = true) @RequestParam Month month,
            @Parameter(description = "Year", required = true) @RequestParam int year);
}