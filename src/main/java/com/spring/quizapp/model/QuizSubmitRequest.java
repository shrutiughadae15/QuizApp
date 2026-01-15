package com.spring.quizapp.model;
import java.util.Map;
public class QuizSubmitRequest {

    private String username;
    private Map<Integer, String> answers;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Map<Integer, String> getAnswers() { return answers; }
    public void setAnswers(Map<Integer, String> answers) { this.answers = answers; }
}
