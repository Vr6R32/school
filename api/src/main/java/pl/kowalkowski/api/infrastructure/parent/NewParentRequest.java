package pl.kowalkowski.api.infrastructure.parent;


import java.time.LocalDate;

public record NewParentRequest(String firstname, String lastname, LocalDate birthDay) {
}
