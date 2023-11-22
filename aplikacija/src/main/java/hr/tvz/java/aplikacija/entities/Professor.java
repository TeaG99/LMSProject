package hr.tvz.java.aplikacija.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

}
