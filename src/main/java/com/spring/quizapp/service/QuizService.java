package com.spring.quizapp.service;
import com.spring.quizapp.model.*;
import com.spring.quizapp.repository.QuizRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class QuizService {
    private final QuizRepository repository;
    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }
    public List<QuizQuestionResponse> getQuestions() {
        List<QuizQuestionResponse> list = new ArrayList<>();

        for (QuizQuestion q : repository.findAll()) {
            QuizQuestionResponse r = new QuizQuestionResponse();
            r.setId(q.getId());
            r.setQuestion(q.getQuestion());
            r.setOptions(List.of(
                    q.getOptionA(),
                    q.getOptionB(),
                    q.getOptionC(),
                    q.getOptionD()
            ));
            list.add(r);
        }
        return list;
    }

    public QuizResultResponse submitQuiz(QuizSubmitRequest request) {

        int score = 0;
        int total = request.getAnswers().size();

        for (Map.Entry<Integer, String> entry : request.getAnswers().entrySet()) {
            QuizQuestion q = repository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Invalid Question ID"));

            if (q.getCorrectOption().equalsIgnoreCase(entry.getValue())) {
                score++;
            }
        }
        repository.saveResult(request.getUsername(), score, total);

        return new QuizResultResponse(score, total);
    }
    public void addQuestion(QuizQuestion question) {
        repository.saveQuestion(question);
    }
}
