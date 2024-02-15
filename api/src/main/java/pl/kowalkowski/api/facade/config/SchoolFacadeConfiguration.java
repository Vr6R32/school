package pl.kowalkowski.api.facade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.school.SchoolService;
import pl.kowalkowski.api.infrastructure.school.SchoolServiceFactory;
import pl.kowalkowski.api.persistance.SchoolRepository;

@Configuration
@RequiredArgsConstructor
public class SchoolFacadeConfiguration {

    private final SchoolRepository schoolRepository;

    @Bean
    public SchoolFacade schoolFacade(){
        SchoolService schoolService = SchoolServiceFactory.createSchoolService(schoolRepository);
        return new SchoolFacade(schoolService);
    }

}
