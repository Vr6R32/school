package pl.kowalkowski.api.facade;

import lombok.AllArgsConstructor;
import pl.kowalkowski.api.infrastructure.school.SchoolResponse;
import pl.kowalkowski.api.infrastructure.school.SchoolService;

import java.math.BigDecimal;

@AllArgsConstructor
public class SchoolFacade {

    private final SchoolService schoolService;
    public SchoolResponse registerNewSchool(String name, BigDecimal hourPrice) {
        return schoolService.createNewSchool(name, hourPrice);
    }
    public SchoolResponse getSchoolByName(String name) {
        return schoolService.getSchoolByName(name);
    }
}
