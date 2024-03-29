package pl.kowalkowski.api.facade;

import lombok.AllArgsConstructor;
import pl.kowalkowski.api.domain.School;
import pl.kowalkowski.api.infrastructure.school.SchoolDTO;
import pl.kowalkowski.api.infrastructure.school.SchoolResponse;
import pl.kowalkowski.api.infrastructure.school.SchoolService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class SchoolFacade {

    private final SchoolService schoolService;

    public List<SchoolDTO> getAllSchools() {
        return schoolService.getAllSchools();
    }

    public SchoolResponse registerNewSchool(String name, BigDecimal hourPrice) {
        return schoolService.createNewSchool(name, hourPrice);
    }

    public SchoolResponse getSchoolByName(String name) {
        return schoolService.getSchoolByName(name);
    }

    public School getSchoolEntityById(UUID uuid) {
        return schoolService.getSchoolById(uuid);
    }

    public boolean checkSchoolExistsById(UUID schoolId) {
        return schoolService.checkSchoolExistsById(schoolId);
    }

}
