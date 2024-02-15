package pl.kowalkowski.api.facade;

import lombok.AllArgsConstructor;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.infrastructure.child.ChildResponse;
import pl.kowalkowski.api.infrastructure.child.ChildService;
import pl.kowalkowski.api.infrastructure.child.NewChildRequest;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
public class ChildFacade {

    private final ChildService childService;

    public ChildResponse registerNewChild(NewChildRequest request) {
        return childService.registerNewChild(request);
    }

    public ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay) {
        return childService.getChildByLastNameAndBirthDate(lastname, birthDay);
    }

    public Child getChildById(UUID uuid) {
        return childService.getChildById(uuid);
    }
}
