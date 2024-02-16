package pl.kowalkowski.api.infrastructure.attendance;

import pl.kowalkowski.api.domain.Attendance;

import static pl.kowalkowski.api.infrastructure.child.ChildMapper.mapChildToDTO;
import static pl.kowalkowski.api.infrastructure.child.ChildMapper.mapChildToDTOnoRelations;

public class AttendanceMapper {

    private AttendanceMapper() {
    }

    public static AttendanceDTO mapAttendanceToDTO(Attendance attendance) {
        return AttendanceDTO.builder()
                .id(attendance.getId())
                .child(mapChildToDTO(attendance.getChild()))
                .entryDate(attendance.getEntryDate())
                .exitDate(attendance.getExitDate())
                .build();
    }

    public static AttendanceDTO mapAttendanceToDTOnoRelations(Attendance attendance) {
        return AttendanceDTO.builder()
                .id(attendance.getId())
                .child(mapChildToDTOnoRelations(attendance.getChild()))
                .entryDate(attendance.getEntryDate())
                .exitDate(attendance.getExitDate())
                .build();
    }

}
