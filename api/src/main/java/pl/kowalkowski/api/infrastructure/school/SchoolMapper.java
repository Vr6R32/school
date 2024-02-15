package pl.kowalkowski.api.infrastructure.school;

import pl.kowalkowski.api.domain.School;

public class SchoolMapper {

    private SchoolMapper() {
    }

    public static SchoolDTO mapSchoolToDTO(School school){
        return SchoolDTO.builder()
                .id(school.getId())
                .name(school.getName())
                .hourPrice(school.getHourPrice())
                .build();
    }
}
