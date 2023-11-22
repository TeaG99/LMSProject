package hr.tvz.java.aplikacija.controllers;

import hr.tvz.java.aplikacija.entities.*;
import hr.tvz.java.aplikacija.repositories.*;
import hr.tvz.java.aplikacija.service.ExamAttemptService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final ExamAttemptRepository examAttemptRepository;
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final ExamAttemptService examAttemptService;

    @Autowired
    public Controller(ProfessorRepository professorRepository, StudentRepository studentRepository, ExamAttemptRepository examAttemptRepository,
                      ExamRepository examRepository, QuestionRepository questionRepository, ExamAttemptService examAttemptService) {
        this.professorRepository = professorRepository;
        this.examAttemptService = examAttemptService;
        this.studentRepository = studentRepository;
        this.examAttemptRepository = examAttemptRepository;
        this.examRepository = examRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/home")
    public String homePage(Model model1,Model model2) {
        model1.addAttribute("professor", new Professor());
        model2.addAttribute("student", new Student());
        return "home";
    }

    @PostMapping("/professor/login")
    public String login(@ModelAttribute("professor") Professor professor, RedirectAttributes redirectAttributes) {
        Optional<Object> foundProfessor = professorRepository.findProfessorByEmailAndPassword(professor.getEmail(), professor.getPassword());

        if (foundProfessor.isPresent()) {
            return "professor";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Wrong credentials");
            return "home";
        }
    }

    @GetMapping("/professor/login")
    public String getProfessorPage(){
        return "professor";
    }

    @PostMapping("/student/login")
    public String exam(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes, HttpSession session) {
        Optional<Object> foundStudent = studentRepository.findStudentByEmailAndPassword(student.getEmail(), student.getPassword());

        if (foundStudent.isPresent()) {
            session.setAttribute("loggedStudent",foundStudent.get());
            return "redirect:examList";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Wrong credentials");
            return "home";
        }
    }
    @PostMapping("/exam-attempts")
    public ExamAttempt saveExamAttempt(HttpSession session, @RequestBody ExamAttempt examAttempt) {
        Student loggedStudent = (Student) session.getAttribute("loggedStudent");
        examAttempt.setStudent(loggedStudent);
        return examAttemptService.saveExamAttempt(examAttempt);
    }


    @GetMapping("/exam")
    @ResponseBody
    public List<Question> getQuestions(HttpSession session) {
        Long examId = (Long) session.getAttribute("examId");
        List<Question> allQuestions = questionRepository.findAllByExamId(examId);
        return allQuestions;
    }

    @GetMapping("/showExam")
    public String showExam(@RequestParam("examId") Long examId, Model model, HttpSession session) {
        Exam exam = examRepository.findById(examId).orElse(null);
        model.addAttribute("exam", exam);
        session.setAttribute("examId",examId);
        return "exam";
    }

    @GetMapping("student/examList")
    public String getExams(Model model) {
        List<Exam> allExams = examRepository.findAll();
        model.addAttribute("allExams", allExams);
        return "examList";
    }

    @PostMapping("/professor/studentInfo")
    public String viewStudents(Model model) {
        List<ExamAttempt> examAttempts = examAttemptRepository.findAll();
        model.addAttribute("examAttempts", examAttempts);
        return "studentInfo";
    }

    @GetMapping("/professor/questionAdd")
    public String showAddQuestionForm(Model model1,Model model2) {
        model1.addAttribute("question", new Question());
        model2.addAttribute("exam",new Exam());
        return "questionAdd";
    }

    @GetMapping("/addStudent")
        public String showAddStudentForm(Model model) {
            model.addAttribute("student",new Student());
            return "addStudent";
    }

    @PostMapping("/addStudent")
    public String addStudentForm(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "addStudent";
    }

    @GetMapping("/addExam")
    public String showAddSExamForm(Model model) {
        model.addAttribute("exam",new Exam());
        return "addExam";
    }

    @PostMapping("/addExam")
    public String addExamForm(@ModelAttribute Exam exam) {
        exam.setExamAttempts(null);
        exam.setQuestions(null);
        examRepository.save(exam);
        return "addExam";
    }

    @PostMapping("/professor/addQuestion")
    public String addQuestionToExam(@ModelAttribute Question question, @ModelAttribute Exam examId, RedirectAttributes redirectAttributes) {

        Exam examPom = examRepository.findById(Long.valueOf(question.exam.id)).orElse(null);

        if (examPom != null) {
            question.setExam(examPom);
            questionRepository.save(question);
            redirectAttributes.addFlashAttribute("successMessage", "Question added successfully");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add question");
        }

        return "redirect:/professor/questionAdd";
    }

}
