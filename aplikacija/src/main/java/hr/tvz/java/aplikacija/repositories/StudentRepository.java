package hr.tvz.java.aplikacija.repositories;

import hr.tvz.java.aplikacija.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Object> findStudentByEmailAndPassword(String email, String password);

}
