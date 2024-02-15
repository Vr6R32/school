package pl.kowalkowski.api.infrastructure.school;

import java.math.BigDecimal;

public interface SchoolService {

    SchoolResponse createNewSchool(String name, BigDecimal hourPrice);
    SchoolResponse getSchoolByName(String name);
}
