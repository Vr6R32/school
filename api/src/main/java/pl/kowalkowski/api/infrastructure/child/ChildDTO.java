package pl.kowalkowski.api.infrastructure.child;

import lombok.Builder;
import pl.kowalkowski.api.domain.Parent;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ChildDTO(UUID id, String firstname, String lastname, LocalDate birthDay, SchoolDTO school, Parent parent) {


}
