package com.dennis.user_service.repository;

import com.dennis.user_service.dto.UserDto;
import com.dennis.user_service.model.CompanyProfile;
import com.dennis.user_service.model.Role;
import com.dennis.user_service.model.Status;
import com.dennis.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByOtp(String otp);

    List<User> findByStatus(Status status);

    // Add method to find the role by email
    @Query("SELECT u.role FROM User u WHERE u.email = :email")
    Optional<Role> findRoleByEmail(@Param("email") String email);
}
