package pl.kowalkowski.api.infrastructure.attendance;

import pl.kowalkowski.api.domain.Attendance;

import static pl.kowalkowski.api.infrastructure.child.ChildMapper.mapChildToDTO;

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

}
