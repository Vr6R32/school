package pl.kowalkowski.api.infrastructure.child;


import pl.kowalkowski.api.domain.Child;

import java.time.LocalDate;
import java.util.UUID;

public interface ChildService {

    ChildResponse registerNewChild(NewChildRequest request);
    ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay);
    Child getChildById(UUID uuid);

}
