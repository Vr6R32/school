package pl.kowalkowski.api.infrastructure.attendance;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record AttendanceNoRelationDTO(UUID id, LocalDateTime entryDate, LocalDateTime exitDate) {
}
