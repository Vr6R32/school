package pl.kowalkowski.api.infrastructure.child;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record NewChildRequest(String firstname, String lastname, LocalDate birthDay, UUID schoolId, UUID parentId) {
}