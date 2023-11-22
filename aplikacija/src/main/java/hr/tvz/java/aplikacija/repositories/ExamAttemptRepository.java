package hr.tvz.java.aplikacija.repositories;

import hr.tvz.java.aplikacija.entities.ExamAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamAttemptRepository extends JpaRepository<ExamAttempt, Long> {
    List<ExamAttempt> findAll();
}
