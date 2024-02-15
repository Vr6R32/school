package pl.kowalkowski.api.infrastructure.parent;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record NewParentRequest(
        @NotEmpty(message = "FIRSTNAME CANNOT BE EMPTY") String firstname,
        @NotEmpty(message = "LASTNAME CANNOT BE EMPTY") String lastname,
        @NotNull(message = "BIRTHDAY CANNOT BE EMPTY")
        @PastOrPresent(message = "BIRTHDAY MUST BE A DATE IN PAST OR PRESENT TIME") LocalDate birthDay

) {
}
