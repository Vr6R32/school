package pl.kowalkowski.api.infrastructure.school;

import pl.kowalkowski.api.domain.School;

import java.math.BigDecimal;
import java.util.UUID;

public interface SchoolService {

    SchoolResponse createNewSchool(String name, BigDecimal hourPrice);
    SchoolResponse getSchoolByName(String name);
    School getSchoolById(UUID uuid);
}
