package com.dennis.user_service.repository;

import com.dennis.user_service.model.CompanyProfile;
import com.dennis.user_service.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {
    List<CompanyProfile> findByUser_Status(Status status);

}
