package pl.kowalkowski.api.infrastructure.school;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import pl.kowalkowski.api.domain.School;
import pl.kowalkowski.api.persistance.SchoolRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static pl.kowalkowski.api.infrastructure.school.SchoolMapper.mapSchoolToDTO;

@RequiredArgsConstructor
class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    @Override
    public List<SchoolDTO> getAllSchools() {
        return schoolRepository.findAll()
                .stream()
                .map(SchoolMapper::mapSchoolToDTO)
                .toList();
    }

    @Override
    public SchoolResponse createNewSchool(String name, BigDecimal hourPrice) {

        schoolRepository.findByNameIgnoreCase(name)
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
        SchoolDTO school = schoolRepository.findByNameIgnoreCase(name)
                .map(SchoolMapper::mapSchoolToDTO)
                .orElseThrow(() -> new SchoolException("SCHOOL WITH NAME " + "[" + name + "]" + " DOESNT EXISTS"));
        return new SchoolResponse("SCHOOL FOUND", HttpStatus.OK, school);
    }

    @Override
    public School getSchoolById(UUID uuid) {
        return schoolRepository.findById(uuid)
                .orElseThrow(() -> new SchoolException("SCHOOL WITH ID " + "[" + uuid + "]" + " DOESNT EXISTS"));
    }

    @Override
    public boolean checkSchoolExistsById(UUID schoolId) {
        return schoolRepository.existsById(schoolId);
    }

}
