package pl.kowalkowski.api.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.kowalkowski.api.domain.Attendance;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {

    @Query("SELECT a FROM Attendance a " +
            "LEFT JOIN FETCH a.child c " +
            "LEFT JOIN FETCH c.school s " +
            "LEFT JOIN FETCH c.parents p " +
            "WHERE c.school.id = ?1 " +
            "AND MONTH(a.entryDate) = ?2 " +
            "AND YEAR(a.entryDate) = ?3")
    List<Attendance> findAttendancesBySchoolIdAndMonth(UUID schoolId, int month, int year);

    @Query("SELECT a FROM Attendance a " +
            "LEFT JOIN FETCH a.child c " +
            "LEFT JOIN FETCH c.school s " +
            "LEFT JOIN c.parents p " +
            "WHERE p.id = ?1 " +
            "AND MONTH(a.entryDate) = ?2 " +
            "AND YEAR(a.entryDate) = ?3")
    List<Attendance> findAttendancesByParentIdAndMonth(UUID parentId, int month, int year);

}