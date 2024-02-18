package pl.kowalkowski.api.facade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.infrastructure.parent.ParentService;
import pl.kowalkowski.api.infrastructure.parent.ParentServiceFactory;
import pl.kowalkowski.api.persistance.ParentRepository;

@Configuration
@RequiredArgsConstructor
class ParentFacadeConfiguration {

    private final ParentRepository parentRepository;

    @Bean
    public ParentFacade parentFacade() {
        ParentService parentService = ParentServiceFactory.createParentService(parentRepository);
        return new ParentFacade(parentService);
    }

}
