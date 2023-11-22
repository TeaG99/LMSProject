package hr.tvz.java.aplikacija.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String title;

    @OneToMany(mappedBy = "exam")
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "exam")
    @JsonIgnore
    private List<ExamAttempt> examAttempts = new ArrayList<>();

    public Exam() {
        this.id = id;
        this.title = title;
        this.questions = questions;
        this.examAttempts = examAttempts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<ExamAttempt> getExamAttempts() {
        return examAttempts;
    }

    public void setExamAttempts(List<ExamAttempt> examAttempts) {
        this.examAttempts = examAttempts;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questionCount=" + questions.size() +
                ", examAttemptCount=" + examAttempts.size() +
                '}';
    }
}