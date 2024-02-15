package pl.kowalkowski.api.infrastructure.school;

import pl.kowalkowski.api.persistance.SchoolRepository;

public class SchoolServiceFactory {

    private SchoolServiceFactory() {
    }

    public static SchoolService createSchoolService(SchoolRepository schoolRepository) {
        return new SchoolServiceImpl(schoolRepository);
    }
}
