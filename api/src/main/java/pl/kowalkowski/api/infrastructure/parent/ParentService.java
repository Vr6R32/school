package pl.kowalkowski.api.infrastructure.parent;


import pl.kowalkowski.api.domain.Parent;

import java.time.LocalDate;
import java.util.UUID;

public interface ParentService {

    ParentResponse registerNewParent(NewParentRequest request);

    ParentResponse getParentByLastNameAndBirthDate(String lastname, LocalDate birthDay);

    Parent getParentById(UUID parentId);


}
