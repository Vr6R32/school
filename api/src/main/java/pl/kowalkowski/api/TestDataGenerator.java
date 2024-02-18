package pl.kowalkowski.api;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import pl.kowalkowski.api.domain.Attendance;
import pl.kowalkowski.api.domain.Child;
import pl.kowalkowski.api.domain.Parent;
import pl.kowalkowski.api.domain.School;
import pl.kowalkowski.api.persistance.AttendanceRepository;
import pl.kowalkowski.api.persistance.ChildRepository;
import pl.kowalkowski.api.persistance.ParentRepository;
import pl.kowalkowski.api.persistance.SchoolRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class TestDataGenerator {

    private final SchoolRepository schoolRepository;
    private final ChildRepository childRepository;
    private final AttendanceRepository attendanceRepository;
    private final ParentRepository parentRepository;

    private final Faker faker = new Faker();
    private final Random random = new Random();

    public TestDataGenerator(SchoolRepository schoolRepository, ChildRepository childRepository,
                             AttendanceRepository attendanceRepository, ParentRepository parentRepository) {
        this.schoolRepository = schoolRepository;
        this.childRepository = childRepository;
        this.attendanceRepository = attendanceRepository;
        this.parentRepository = parentRepository;
    }

    //    @PostConstruct
    public void generateTestData() {
        //TODO just uncomment @PostConstruct annotation to generate more example data
        generateParents();
        generateSchools();
        generateChildren();
        generateAttendanceRecords();
    }

    private void generateParents() {
        List<Parent> parents = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Parent parent = Parent.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .birthDay(generateRandomBirthDate(1973, 1998))
                    .build();
            parents.add(parent);
        }

        parentRepository.saveAll(parents);
    }

    private void generateSchools() {
        List<School> schools = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            School school = School.builder()
                    .name(faker.company().industry() + " School")
                    .hourPrice(BigDecimal.valueOf(random.nextInt(10, 30)))
                    .build();
            schools.add(school);
        }

        schoolRepository.saveAll(schools);
    }

    private void generateChildren() {

        List<Child> childrens = new LinkedList<>();
        List<Parent> parents = parentRepository.findAll();
        List<School> schools = schoolRepository.findAll();

        for (int i = 1; i <= 100; i++) {

            Parent parent = parents.get(i % parents.size());
            Parent parent2 = parents.get((i + 1) % parents.size());
            School school = schools.get(i % schools.size());

            Child child = Child.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .school(school)
                    .parents(Set.of(parent, parent2))
                    .birthDay(generateRandomBirthDate(2015, 2020))
                    .build();
            childrens.add(child);
        }

        childRepository.saveAll(childrens);
    }

    private LocalDate generateRandomBirthDate(int minYear, int maxYear) {
        int year = ThreadLocalRandom.current().nextInt(minYear, maxYear + 1);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day = ThreadLocalRandom.current().nextInt(1, LocalDate.of(year, month, 1).lengthOfMonth() + 1);
        return LocalDate.of(year, month, day);
    }

    private void generateAttendanceRecords() {
        List<Attendance> attendanceRecords = new ArrayList<>();
        List<Child> children = childRepository.findAll();

        LocalDate currentDate = LocalDate.of(2023, 1, 1);
        LocalDateTime currentDateTime = LocalDateTime.of(currentDate, LocalTime.of(6, 0));

        for (int i = 1; i <= 730; i++) {
            Child child = children.get(i % children.size());

            int entryHour = random.nextInt(11) + 6;
            int exitHour = entryHour + random.nextInt(6) + 1;

            LocalDateTime entryDateTime = currentDateTime.withHour(entryHour).withMinute(random.nextInt(60));
            LocalDateTime exitDateTime = currentDateTime.withHour(exitHour).withMinute(random.nextInt(60));

            int randomAttendances = random.nextInt(3, 12);

            for (int j = 1; j <= randomAttendances; j++) {

                LocalDateTime entryDateTimeNextDay = entryDateTime.plusDays(j).withHour(entryDateTime.getHour()).withMinute(entryDateTime.getMinute());
                LocalDateTime exitDateTimeNextDay = exitDateTime.plusDays(j).withHour(exitDateTime.getHour()).withMinute(exitDateTime.getMinute());
                Attendance additionalAttendance = Attendance.builder()
                        .child(child)
                        .entryDate(entryDateTimeNextDay)
                        .exitDate(exitDateTimeNextDay)
                        .build();

                attendanceRecords.add(additionalAttendance);
            }


            currentDateTime = currentDateTime.plusDays(1);
        }

        attendanceRepository.saveAll(attendanceRecords);
    }
}