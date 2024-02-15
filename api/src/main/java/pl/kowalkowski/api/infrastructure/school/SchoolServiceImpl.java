package pl.kowalkowski.api.infrastructure.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.School;
import pl.kowalkowski.api.persistance.SchoolRepository;

import java.math.BigDecimal;

import static pl.kowalkowski.api.infrastructure.school.SchoolMapper.mapSchoolToDTO;

@RequiredArgsConstructor
class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public SchoolResponse createNewSchool(String name, BigDecimal hourPrice) {

        schoolRepository.findByName(name)
                .ifPresent(existingSchool -> {
                    throw new SchoolException("SCHOOL WITH NAME " + "[" + name + "]" + " ALREADY EXISTS");
                });

        School newSchool = School.builder()
                .name(name)
                .hourPrice(hourPrice)
                .build();
        schoolRepository.save(newSchool);

        return new SchoolResponse("SCHOOL_CREATED", HttpStatus.OK, mapSchoolToDTO(newSchool));
    }

    @Override
    public SchoolResponse getSchoolByName(String name) {
        SchoolDTO school = schoolRepository.findByName(name)
                .map(SchoolMapper::mapSchoolToDTO)
                .orElseThrow(() -> new SchoolException("SCHOOL WITH NAME " + "[" + name + "]" + " DOESNT EXISTS"));
        return new SchoolResponse("SCHOOL FOUND", HttpStatus.OK, school);
    }
}
