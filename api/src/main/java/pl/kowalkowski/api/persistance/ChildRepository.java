package pl.kowalkowski.api.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kowalkowski.api.domain.Child;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChildRepository extends JpaRepository<Child, UUID> {

    @Query("SELECT c FROM Child c " +
            "LEFT JOIN FETCH c.school " +
            "LEFT JOIN FETCH c.parents " +
            "WHERE c.lastname = :lastname AND c.birthDay = :birthDay")
    Optional<Child> findByLastnameIgnoreCaseAndBirthDay(String lastname, LocalDate birthDay);

}
