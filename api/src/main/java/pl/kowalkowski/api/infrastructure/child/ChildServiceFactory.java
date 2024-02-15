package pl.kowalkowski.api.infrastructure.child;

import pl.kowalkowski.api.persistance.ChildRepository;

public class ChildServiceFactory {

    private ChildServiceFactory() {
    }

    public static ChildService createChildService(ChildRepository childRepository) {
        return new ChildServiceImpl(childRepository);
    }
}
