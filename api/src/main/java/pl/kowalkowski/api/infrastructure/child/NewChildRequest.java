package pl.kowalkowski.api.infrastructure.child;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record NewChildRequest(
        @NotEmpty(message = "FIRSTNAME CANNOT BE EMPTY") String firstname,
        @NotEmpty(message = "LASTNAME CANNOT BE EMPTY") String lastname,
        @NotNull(message = "BIRTHDAY CANNOT BE EMPTY")
        @PastOrPresent(message = "BIRTHDAY MUST BE A DATE IN PAST OR PRESENT TIME") LocalDate birthDay,
        UUID schoolId,
        UUID parentId

) {
}