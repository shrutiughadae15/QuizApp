package com.spring.quizapp.service;

import com.spring.quizapp.model.QuizQuestion;
import com.spring.quizapp.model.QuizSubmitRequest;
import com.spring.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    // Get all questions (hide correct answers)
    public List<QuizQuestion> getQuestions() {
        List<QuizQuestion> questions = quizRepository.getAllQuestions();
        for (QuizQuestion q : questions) {
            q.setCorrectOption(null); // hide correct answer
        }
        return questions;
    }

    // Add new question
    public void addQuestion(QuizQuestion question) {
        quizRepository.addQuestion(question);
    }

    // Submit quiz answers
    public int submitQuiz(QuizSubmitRequest request) {
        Map<String, String> answers = request.getAnswers();
        if (answers == null) throw new RuntimeException("Answers cannot be null");

        int score = 0;
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            int questionId = Integer.parseInt(entry.getKey());
            String userAnswer = entry.getValue();

            QuizQuestion q = quizRepository.getQuestionById(questionId)
                    .orElseThrow(() -> new RuntimeException("Invalid Question ID: " + questionId));

            if (q.getCorrectOption().equalsIgnoreCase(userAnswer)) {
                score++;
            }
        }
        return score;
    }
}
