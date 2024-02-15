package pl.kowalkowski.api.infrastructure.child;


import pl.kowalkowski.api.domain.Child;

import static pl.kowalkowski.api.infrastructure.parent.ParentMapper.mapParentToDTO;
import static pl.kowalkowski.api.infrastructure.school.SchoolMapper.mapSchoolToDTO;

public class ChildMapper {

    private ChildMapper() {
    }

    public static ChildDTO mapChildToDTO(Child child) {
        return ChildDTO.builder()
                .id(child.getId())
                .firstname(child.getFirstname())
                .lastname(child.getLastname())
                .birthDay(child.getBirthDay())
                .school(mapSchoolToDTO(child.getSchool()))
                .parent(mapParentToDTO(child.getParent()))
                .build();
    }
}



