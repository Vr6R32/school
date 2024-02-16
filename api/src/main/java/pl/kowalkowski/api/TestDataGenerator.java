package pl.kowalkowski.api;

import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import pl.kowalkowski.api.domain.*;
import pl.kowalkowski.api.persistance.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @PostConstruct
    public void generateTestData() {
        generateParents();
        generateSchools();
        generateChildren();
        generateAttendanceRecords();
    }

    private void generateParents() {
        List<Parent> parents = new ArrayList<>();
        LocalDate parentBirthDate = LocalDate.of(1980, 1, 1);

        for (int i = 1; i <= 10; i++) {
            Parent parent = Parent.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .birthDay(parentBirthDate.plusDays(i))
                    .build();
            parents.add(parent);
        }

        parentRepository.saveAll(parents);
    }

    private void generateSchools() {
        List<School> schools = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            School school = School.builder()
                    .name(faker.company().name())
                    .hourPrice(BigDecimal.valueOf(10L + i))
                    .build();
            schools.add(school);
        }

        schoolRepository.saveAll(schools);
    }

    private void generateChildren() {


        List<Child> children = new ArrayList<>();
        LocalDate childBirthDate = LocalDate.of(2010, 1, 1);

        List<Parent> parents = parentRepository.findAll();
        List<School> schools = schoolRepository.findAll();

        for (int i = 1; i <= 100; i++) {
            Parent parent = parents.get(i % parents.size());
            School school = schools.get(i % schools.size());


            Child child = Child.builder()
                    .firstname(faker.name().firstName())
                    .lastname(faker.name().lastName())
                    .school(school)
                    .parent(parent)
                    .birthDay(childBirthDate.plusDays(i))
                    .build();
            children.add(child);
        }

        childRepository.saveAll(children);
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

            Attendance attendance = Attendance.builder()
                    .child(child)
                    .entryDate(entryDateTime)
                    .exitDate(exitDateTime)
                    .build();

            attendanceRecords.add(attendance);

            LocalDateTime entryDateTimeNextDay = currentDateTime.plusDays(1).withHour(entryHour).withMinute(random.nextInt(60));
            LocalDateTime exitDateTimeNextDay = currentDateTime.plusDays(1).withHour(exitHour).withMinute(random.nextInt(60));

            Attendance attendance2 = Attendance.builder()
                    .child(child)
                    .entryDate(entryDateTimeNextDay)
                    .exitDate(exitDateTimeNextDay)
                    .build();

            attendanceRecords.add(attendance2);

            currentDateTime = currentDateTime.plusDays(1);
        }

        attendanceRepository.saveAll(attendanceRecords);
    }
}