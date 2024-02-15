package pl.kowalkowski.api.infrastructure.parent;

import pl.kowalkowski.api.persistance.ParentRepository;

public class ParentServiceFactory {

    private ParentServiceFactory() {
    }

    public static ParentService createParentService(ParentRepository parentRepository) {
        return new ParentServiceImpl(parentRepository);
    }
}
