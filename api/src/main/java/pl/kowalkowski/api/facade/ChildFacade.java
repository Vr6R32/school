package pl.kowalkowski.api.facade;

import lombok.AllArgsConstructor;
import pl.kowalkowski.api.infrastructure.child.ChildResponse;
import pl.kowalkowski.api.infrastructure.child.ChildService;
import pl.kowalkowski.api.infrastructure.child.NewChildRequest;

import java.time.LocalDate;

@AllArgsConstructor
public class ChildFacade {

    private final ChildService childService;

    public ChildResponse registerNewChild(NewChildRequest request) {
        return childService.createNewChild(request);
    }

    public ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay) {
        return childService.getChildByLastNameAndBirthDate(lastname, birthDay);
    }
}
