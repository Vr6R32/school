package pl.kowalkowski.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.infrastructure.child.ChildResponse;
import pl.kowalkowski.api.infrastructure.child.NewChildRequest;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/childs")
public class ChildController {

    private final ChildFacade childFacade;

    @PostMapping
    public ChildResponse registerNewChild(@Valid @RequestBody NewChildRequest request){
        return childFacade.registerNewChild(request);
    }

    @GetMapping
    public ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthDay){
        // TODO IT's extra endpoint it can produce non unique query result
        //  further implementation depends on bussines needs
        return childFacade.getChildByLastNameAndBirthDate(lastname,birthDay);
    }

}
