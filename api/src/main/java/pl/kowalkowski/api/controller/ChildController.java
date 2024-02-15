package pl.kowalkowski.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.infrastructure.child.ChildResponse;
import pl.kowalkowski.api.infrastructure.child.NewChildRequest;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/childs")
@RequiredArgsConstructor
public class ChildController {

    private final ChildFacade childFacade;

    @PostMapping
    public ChildResponse registerNewChild(NewChildRequest request){
        return childFacade.registerNewChild(request);
    }

    @GetMapping
    public ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay){
        return childFacade.getChildByLastNameAndBirthDate(lastname,birthDay);
    }

}
