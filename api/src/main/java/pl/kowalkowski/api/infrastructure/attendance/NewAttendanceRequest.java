package pl.kowalkowski.api.infrastructure.attendance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder

public record NewAttendanceRequest(
        @NotNull(message = "CHILD ID CANNOT BE EMPTY") UUID childId,
        @NotNull(message = "ENTRY DATE CANNOT BE EMPTY") LocalDateTime entryDate,
        @NotNull(message = "EXIT DATE CANNOT BE EMPTY") LocalDateTime exitDate) {

    @AssertTrue(message = "ENTRY DATE CANNOT BE AFTER EXIT DATE")
    private boolean isEntryDateBeforeExitDate() {
        return entryDate.isBefore(exitDate);
    }

}
