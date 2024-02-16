package pl.kowalkowski.api.infrastructure.child;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.domain.Parent;
import pl.kowalkowski.api.domain.School;
import pl.kowalkowski.api.facade.ParentFacade;
import pl.kowalkowski.api.facade.SchoolFacade;
import pl.kowalkowski.api.persistance.ChildRepository;

import java.time.LocalDate;
import java.util.UUID;

import static pl.kowalkowski.api.infrastructure.child.ChildMapper.mapChildToDTO;

@RequiredArgsConstructor
class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;
    private final SchoolFacade schoolFacade;
    private final ParentFacade parentFacade;

    @Override
    public ChildResponse registerNewChild(NewChildRequest request) {

        School school = schoolFacade.getSchoolEntityById(request.schoolId());
        Parent parent = parentFacade.getParentEntityById(request.parentId());

        Child newChild = Child.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .birthDay(request.birthDay())
                .school(school)
                .parent(parent)
                .build();

        childRepository.save(newChild);

        return new ChildResponse("CHILD REGISTERED", HttpStatus.OK, mapChildToDTO(newChild));
    }

    @Override
    public ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthday) {
        ChildDTO child = childRepository.findByLastnameIgnoreCaseAndBirthDay(lastname, birthday)
                .map(ChildMapper::mapChildToDTO)
                .orElseThrow(() -> new ChildException("CHILD WITH LAST NAME [" + lastname + "] AND BIRTHDAY [" + birthday + "] DOESN'T EXISTS"));
        return new ChildResponse("CHILD FOUND", HttpStatus.OK, child);
    }

    @Override
    public Child getChildEntityById(UUID uuid) {
        return childRepository.findById(uuid)
                .orElseThrow(() -> new ChildException("CHILD WITH ID " + "[" + uuid + "]" + " DOESNT EXISTS"));
    }

}
