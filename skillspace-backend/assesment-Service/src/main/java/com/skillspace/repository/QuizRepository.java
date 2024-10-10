package com.skillspace.repository;

import com.skillspace.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByIsGlobalTrue();
    List<Quiz> findByCompanyName(String companyName);
}
