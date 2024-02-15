package pl.kowalkowski.api.infrastructure.attendance;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder

public record NewAttendanceRequest(
        @NotNull(message = "CHILD ID CANNOT BE EMPTY") UUID childId,
        @NotNull(message = "BIRTHDAY CANNOT BE EMPTY") LocalDateTime entryDate,
        @NotNull(message = "BIRTHDAY CANNOT BE EMPTY") LocalDateTime exitDate) {
}
