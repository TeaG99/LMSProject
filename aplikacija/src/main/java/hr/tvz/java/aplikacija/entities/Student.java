package hr.tvz.java.aplikacija.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<ExamAttempt> examAttempts = new ArrayList<>();



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", examAttempts=" + examAttempts +
                '}';
    }
}