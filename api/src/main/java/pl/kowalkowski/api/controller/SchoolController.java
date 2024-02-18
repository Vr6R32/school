package pl.kowalkowski.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;
import pl.kowalkowski.api.infrastructure.school.SchoolResponse;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/schools")
class SchoolController {

    private final SchoolFacade schoolFacade;

    @GetMapping("all")
    public List<SchoolDTO> getAllSchools() {
        return schoolFacade.getAllSchools();
    }

    @PostMapping
    public SchoolResponse registerNewSchool(String name, BigDecimal hourPrice) {
        return schoolFacade.registerNewSchool(name, hourPrice);
    }

    @GetMapping
    public SchoolResponse findSchool(String name) {
        return schoolFacade.getSchoolByName(name);
    }
}
