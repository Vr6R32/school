package pl.kowalkowski.api.facade.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.child.ChildService;
import pl.kowalkowski.api.infrastructure.child.ChildServiceFactory;
import pl.kowalkowski.api.infrastructure.parent.ParentService;
import pl.kowalkowski.api.infrastructure.parent.ParentServiceFactory;
import pl.kowalkowski.api.infrastructure.school.SchoolService;
import pl.kowalkowski.api.infrastructure.school.SchoolServiceFactory;
import pl.kowalkowski.api.persistance.ChildRepository;
import pl.kowalkowski.api.persistance.ParentRepository;
import pl.kowalkowski.api.persistance.SchoolRepository;

@Configuration
@AllArgsConstructor
public class FacadeConfiguration {

    private final SchoolFacade schoolFacade;
    private final ParentFacade parentFacade;
    private final ChildRepository childRepository;
    private final SchoolRepository schoolRepository;
    private final ParentRepository parentRepository;


    @Bean
    public SchoolFacade schoolFacade(){
        SchoolService schoolService = SchoolServiceFactory.createSchoolService(schoolRepository);
        return new SchoolFacade(schoolService);
    }

    @Bean
    public ParentFacade parentFacade(){
        ParentService parentService = ParentServiceFactory.createParentService(parentRepository);
        return new ParentFacade(parentService);
    }

    @Bean
    public ChildFacade childFacade(){
        ChildService childService = ChildServiceFactory.createChildService(childRepository,schoolFacade,parentFacade);
        return new ChildFacade(childService);
    }
}
