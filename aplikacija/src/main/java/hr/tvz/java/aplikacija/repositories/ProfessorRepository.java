package hr.tvz.java.aplikacija.repositories;

import hr.tvz.java.aplikacija.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    Optional<Object> findProfessorByEmailAndPassword(String email, String password);
}
