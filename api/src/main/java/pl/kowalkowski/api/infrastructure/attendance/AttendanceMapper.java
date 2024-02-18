package pl.kowalkowski.api.infrastructure.attendance;

import pl.kowalkowski.api.domain.Attendance;

import java.util.List;

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

    public static List<AttendanceNoRelationDTO> mapAttendanceListToDTOListNoRelations(List<AttendanceDTO> attendanceList) {
        return attendanceList.stream()
                .map(attendance -> AttendanceNoRelationDTO.builder()
                        .id(attendance.id())
                        .entryDate(attendance.entryDate())
                        .exitDate(attendance.exitDate())
                        .build())
                        .toList();
    }

}
