package com.skillspace.service;

import com.skillspace.model.Quiz;

import java.util.List;

public interface QuizService {
    Quiz createQuiz(Quiz quiz);
    Quiz addToGlobalRepository(Quiz quiz);
    List<Quiz> getGlobalQuizzes();
    List<Quiz> getCompanyQuizzes(String companyName);
    Quiz updateQuiz(Long id, Quiz updatedQuiz, String companyName);
    void deleteQuiz(Long id, String companyName);
}
