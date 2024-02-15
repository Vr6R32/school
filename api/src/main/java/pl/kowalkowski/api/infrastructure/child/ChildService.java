package pl.kowalkowski.api.infrastructure.child;


import java.time.LocalDate;

public interface ChildService {

    ChildResponse createNewChild(NewChildRequest request);
    ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay);

}
