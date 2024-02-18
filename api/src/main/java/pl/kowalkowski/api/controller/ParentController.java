package pl.kowalkowski.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.infrastructure.parent.NewParentRequest;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;
import pl.kowalkowski.api.infrastructure.parent.ParentResponse;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/parents")
class ParentController {

    private final ParentFacade parentFacade;


    @GetMapping("all")
    public List<ParentDTO> getAllParents() {
        return parentFacade.getAllParents();
    }

    @PostMapping
    public ParentResponse registerNewParent(@Valid @RequestBody NewParentRequest request) {
        return parentFacade.registerNewParent(request);
    }

    @GetMapping
    public ParentResponse getParentByLastNameAndBirthDate(String lastname, LocalDate birthDay) {
        // TODO it's extra endpoint it can produce non unique query result
        //  further implementation depends on needs
        return parentFacade.getParentByLastNameAndBirthDate(lastname, birthDay);
    }

}

