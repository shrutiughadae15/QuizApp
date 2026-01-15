package com.spring.quizapp.controller;
import com.spring.quizapp.model.*;
import com.spring.quizapp.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @GetMapping("/questions")
    public ResponseEntity<List<QuizQuestionResponse>> getQuestions() {
        return ResponseEntity.ok(service.getQuestions());
    }

    @PostMapping("/question")
    public ResponseEntity<Void> addQuestion(@RequestBody QuizQuestion question) {
        service.addQuestion(question);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultResponse> submitQuiz(
            @RequestBody QuizSubmitRequest request) {
        return ResponseEntity.ok(service.submitQuiz(request));
    }
}
