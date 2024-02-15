package pl.kowalkowski.api.infrastructure.child;


import java.time.LocalDate;

public interface ChildService {

    ChildResponse registerNewChild(NewChildRequest request);
    ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay);

}
