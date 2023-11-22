package hr.tvz.java.aplikacija.service;

import hr.tvz.java.aplikacija.entities.ExamAttempt;
import hr.tvz.java.aplikacija.entities.Student;
import hr.tvz.java.aplikacija.repositories.ExamAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class ExamAttemptService {

        private final ExamAttemptRepository examAttemptRepository;

        @Autowired
        public ExamAttemptService(ExamAttemptRepository examAttemptRepository) {
            this.examAttemptRepository = examAttemptRepository;
        }

        public ExamAttempt saveExamAttempt(ExamAttempt examAttempt) {
            return examAttemptRepository.save(examAttempt);
        }
    }

