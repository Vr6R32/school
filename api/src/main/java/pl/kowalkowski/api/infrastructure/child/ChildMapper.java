package pl.kowalkowski.api.infrastructure.child;


import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.infrastructure.parent.ParentDTO;
import pl.kowalkowski.api.infrastructure.parent.ParentMapper;

import java.util.Set;
import java.util.stream.Collectors;

import static pl.kowalkowski.api.infrastructure.school.SchoolMapper.mapSchoolToDTO;

public class ChildMapper {

    private ChildMapper() {
    }

    public static ChildDTO mapChildToDTO(Child child) {

        Set<ParentDTO> parents = child.getParents()
                .stream()
                .map(ParentMapper::mapParentToDTO)
                .collect(Collectors.toSet());

        return ChildDTO.builder()
                .id(child.getId())
                .firstname(child.getFirstname())
                .lastname(child.getLastname())
                .birthDay(child.getBirthDay())
                .school(mapSchoolToDTO(child.getSchool()))
                .parent(parents)
                .build();
    }

    public static ChildDTO mapChildToDTOnoRelations(Child child) {
        return ChildDTO.builder()
                .id(child.getId())
                .firstname(child.getFirstname())
                .lastname(child.getLastname())
                .birthDay(child.getBirthDay())
                .build();
    }
}



