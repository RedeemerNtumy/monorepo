package com.skillspace.repository;

import com.skillspace.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgramRepository extends JpaRepository<Program, Long> {

    List<Program> findByIsDraftAndDeletedFalse(boolean isDraft);

    Optional<Program> findByIdAndDeletedFalse(Long id);
}
