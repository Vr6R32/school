package pl.kowalkowski.api.facade;

import lombok.AllArgsConstructor;
import pl.kowalkowski.api.domain.Parent;
import pl.kowalkowski.api.infrastructure.parent.NewParentRequest;
import pl.kowalkowski.api.infrastructure.parent.ParentResponse;
import pl.kowalkowski.api.infrastructure.parent.ParentService;

import java.time.LocalDate;
import java.util.UUID;


@AllArgsConstructor
public class ParentFacade {

    private final ParentService parentService;

    public ParentResponse registerNewParent(NewParentRequest request) {
        return parentService.registerNewParent(request);
    }
    public ParentResponse getParentByLastNameAndBirthDate(String lastname, LocalDate birthDay) {
        return parentService.getParentByLastNameAndBirthDate(lastname,birthDay);
    }

    public Parent getParentEntityById(UUID parentId) {
        return parentService.getParentById(parentId);
    }
}
