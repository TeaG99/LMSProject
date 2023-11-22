package hr.tvz.java.aplikacija.service;

import hr.tvz.java.aplikacija.entities.Professor;
import hr.tvz.java.aplikacija.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor getProfessorByEmailAndPassword(String email, String password) {
        return (Professor) professorRepository.findProfessorByEmailAndPassword(email,password).orElse(null);
    }
}
