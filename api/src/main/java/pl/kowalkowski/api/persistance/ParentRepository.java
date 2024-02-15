package pl.kowalkowski.api.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kowalkowski.api.domain.Parent;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParentRepository extends JpaRepository<Parent, UUID> {

    Optional<Parent> findByLastnameIgnoreCaseAndBirthDay(String lastname, LocalDate birthDay);


}
