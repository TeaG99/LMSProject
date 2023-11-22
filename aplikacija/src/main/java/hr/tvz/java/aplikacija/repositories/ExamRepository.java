package hr.tvz.java.aplikacija.repositories;

import hr.tvz.java.aplikacija.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
}
