package pl.kowalkowski.api.facade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.infrastructure.child.ChildService;
import pl.kowalkowski.api.infrastructure.child.ChildServiceFactory;
import pl.kowalkowski.api.persistance.ChildRepository;

@Configuration
@RequiredArgsConstructor
public class ChildFacadeConfiguration {

    private final SchoolFacade schoolFacade;
    private final ParentFacade parentFacade;
    private final ChildRepository childRepository;

    @Bean
    public ChildFacade childFacade() {
        ChildService childService = ChildServiceFactory.createChildService(childRepository,schoolFacade,parentFacade);
        return new ChildFacade(childService);
    }
}
