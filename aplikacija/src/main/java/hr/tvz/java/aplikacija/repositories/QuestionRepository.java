package hr.tvz.java.aplikacija.repositories;

import hr.tvz.java.aplikacija.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAll();
    List<Question> findAllByExamId(Long id);
}
