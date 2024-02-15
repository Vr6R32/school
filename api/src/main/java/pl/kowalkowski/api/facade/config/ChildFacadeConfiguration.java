package pl.kowalkowski.api.facade.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.ChildFacade;
import pl.kowalkowski.api.infrastructure.child.ChildService;
import pl.kowalkowski.api.infrastructure.child.ChildServiceFactory;
import pl.kowalkowski.api.persistance.ChildRepository;

@Configuration
@AllArgsConstructor
public class ChildFacadeConfiguration {

    private final ChildRepository childRepository;

    @Bean
    public ChildFacade childFacade(){
        ChildService childService = ChildServiceFactory.createChildService(childRepository);
        return new ChildFacade(childService);
    }

}
