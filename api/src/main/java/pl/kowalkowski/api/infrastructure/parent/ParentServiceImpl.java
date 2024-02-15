package pl.kowalkowski.api.infrastructure.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.Parent;
import pl.kowalkowski.api.persistance.ParentRepository;

import java.time.LocalDate;
import java.util.UUID;

import static pl.kowalkowski.api.infrastructure.parent.ParentMapper.mapParentToDTO;

@RequiredArgsConstructor
class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public ParentResponse registerNewParent(NewParentRequest request) {

        Parent newParent = Parent.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .birthDay(request.birthDay())
                .build();

        parentRepository.save(newParent);

        return new ParentResponse("PARENT REGISTERED", HttpStatus.OK, mapParentToDTO(newParent));
    }

    @Override
    public ParentResponse getParentByLastNameAndBirthDate(String lastname, LocalDate birthDay) {
        ParentDTO parent = parentRepository.findByLastnameAndBirthDay(lastname, birthDay)
                .map(ParentMapper::mapParentToDTO)
                .orElseThrow(() -> new ParentException("PARENT WITH LAST NAME [" + lastname + "] AND BIRTHDAY [" + birthDay + "] DOESN'T EXISTS"));
        return new ParentResponse("PARENT FOUND", HttpStatus.OK, parent);
    }

    @Override
    public Parent getParentById(UUID uuid) {
        return parentRepository.findById(uuid)
                .orElseThrow(() -> new ParentException("PARENT WITH ID " + "[" + uuid + "]" + " DOESNT EXISTS"));
    }

}
