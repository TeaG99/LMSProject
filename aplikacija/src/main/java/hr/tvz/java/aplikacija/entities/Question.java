package hr.tvz.java.aplikacija.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    public Exam exam;

}