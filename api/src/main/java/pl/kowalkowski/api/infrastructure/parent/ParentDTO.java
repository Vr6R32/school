package pl.kowalkowski.api.infrastructure.parent;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ParentDTO(UUID id, LocalDate birthDay, String firstname, String lastname) {
}


