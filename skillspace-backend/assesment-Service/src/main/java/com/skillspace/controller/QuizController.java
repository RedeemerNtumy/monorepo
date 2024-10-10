package com.skillspace.controller;

import com.skillspace.model.Quiz;
import com.skillspace.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.created;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(createdQuiz);
    }

    @PostMapping("/global")
    public ResponseEntity<Quiz> addToGlobalRepository(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.addToGlobalRepository(quiz));
    }

    @GetMapping("/global")
    public List<Quiz> getGlobalQuizzes() {
        return quizService.getGlobalQuizzes();
    }

    @GetMapping("/company/{companyName}")
    public List<Quiz> getCompanyQuizzes(@PathVariable String companyName) {
        return quizService.getCompanyQuizzes(companyName);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz updatedQuiz, @RequestHeader("Company-Name") String companyName) {
        return ResponseEntity.ok(quizService.updateQuiz(id, updatedQuiz, companyName));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id, @RequestHeader("Company-Name") String companyName) {
        quizService.deleteQuiz(id, companyName);
        return ResponseEntity.noContent().build();
    }
}
