package com.skillspace.service;

import com.skillspace.exception.QuizNotFoundException;
import com.skillspace.exception.UnauthorizedAccessException;
import com.skillspace.model.Quiz;
import com.skillspace.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public Quiz addToGlobalRepository(Quiz quiz) {
        quiz.setGlobal(true);
        return quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getGlobalQuizzes() {
        return quizRepository.findByIsGlobalTrue();
    }

    @Override
    public List<Quiz> getCompanyQuizzes(String companyName) {
        return quizRepository.findByCompanyName(companyName);
    }

    @Override
    public Quiz updateQuiz(Long id, Quiz updatedQuiz, String companyName) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + id));

        if (!quiz.getCompanyName().equals(companyName)) {
            throw new UnauthorizedAccessException("Unauthorized to update this quiz");
        }

        quiz.setTitle(updatedQuiz.getTitle());
        quiz.setSkill(updatedQuiz.getSkill());
        quiz.setGlobal(updatedQuiz.isGlobal());
        quiz.setTimeLimit(updatedQuiz.getTimeLimit());
        quiz.setQuestions(updatedQuiz.getQuestions());

        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long id, String companyName) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz not found with id: " + id));

        if (!quiz.getCompanyName().equals(companyName)) {
            throw new UnauthorizedAccessException("Unauthorized to delete this quiz");
        }

        quizRepository.deleteById(id);
    }
}
