package com.skillspace.repository;

import com.skillspace.model.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
    List<ChangeLog> findByProgramIdOrderByChangeDateDesc(Long programId);

    List<ChangeLog> findAllByOrderByChangeDateDesc();
}

