package pl.kowalkowski.api.infrastructure.child;

import lombok.Builder;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Builder
public record ChildDTO(UUID id, String firstname, String lastname, LocalDate birthDay, SchoolDTO school, Set<ParentDTO> parent) {
}
