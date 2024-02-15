package pl.kowalkowski.api.infrastructure.child;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.persistance.ChildRepository;

import java.time.LocalDate;

@RequiredArgsConstructor
class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;

    @Override
    public ChildResponse createNewChild(NewChildRequest request) {
        return null;
    }

    @Override
    public ChildResponse getChildByLastNameAndBirthDate(String lastname, LocalDate birthday) {
        ChildDTO child = childRepository.findByLastnameAndBirthDay(lastname, birthday)
                .map(ChildMapper::mapChildToDTO)
                .orElseThrow(() -> new ChildException("CHILD WITH LAST NAME [" + lastname + "] AND BIRTHDAY [" + birthday + "] DOESN'T EXISTS"));
        return new ChildResponse("CHILD FOUND", HttpStatus.OK, child);
    }
}
