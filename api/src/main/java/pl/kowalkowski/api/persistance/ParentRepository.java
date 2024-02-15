package pl.kowalkowski.api.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kowalkowski.api.domain.Parent;

import java.util.UUID;

@Repository
public interface ParentRepository extends JpaRepository<Parent, UUID> {
}
