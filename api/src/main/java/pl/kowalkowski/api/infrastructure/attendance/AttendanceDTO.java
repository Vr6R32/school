package pl.kowalkowski.api.infrastructure.attendance;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.child.ChildDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record AttendanceDTO(UUID id, ChildDTO child, LocalDateTime entryDate, LocalDateTime exitDate) {
}
