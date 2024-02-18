package pl.kowalkowski.api.infrastructure.child;

import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.persistance.ChildRepository;

public class ChildServiceFactory {

    private ChildServiceFactory() {
    }

    public static ChildService createChildService(ChildRepository childRepository, SchoolFacade schoolFacade, ParentFacade parentFacade) {
        return new ChildServiceImpl(childRepository, schoolFacade, parentFacade);
    }
}
