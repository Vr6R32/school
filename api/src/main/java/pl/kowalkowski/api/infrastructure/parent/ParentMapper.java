package pl.kowalkowski.api.infrastructure.parent;

import pl.kowalkowski.api.domain.Parent;

public class ParentMapper {
    private ParentMapper() {
    }

    public static ParentDTO mapParentToDTO(Parent parent) {
        return ParentDTO.builder()
                .id(parent.getId())
                .firstname(parent.getFirstname())
                .lastname(parent.getLastname())
                .birthDay(parent.getBirthDay())
                .build();
    }
}
