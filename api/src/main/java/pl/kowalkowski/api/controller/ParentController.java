package pl.kowalkowski.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.infrastructure.parent.NewParentRequest;
import pl.kowalkowski.api.infrastructure.parent.ParentResponse;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parents")
public class ParentController {

    private final ParentFacade parentFacade;

    @PostMapping
    public ParentResponse registerNewParent(@Valid @RequestBody NewParentRequest request) {
        return parentFacade.registerNewParent(request);
    }

    @GetMapping
    public ParentResponse getParentByLastNameAndBirthDate(String lastname, LocalDate birthDay) {
        return parentFacade.getParentByLastNameAndBirthDate(lastname, birthDay);
    }

}

