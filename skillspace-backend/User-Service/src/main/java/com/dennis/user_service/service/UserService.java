package com.dennis.user_service.service;

import com.dennis.user_service.client.EmailClient;
import com.dennis.user_service.dto.*;
import com.dennis.user_service.model.CompanyProfile;
import com.dennis.user_service.model.Role;
import com.dennis.user_service.model.Status;
import com.dennis.user_service.model.User;
import com.dennis.user_service.repository.CompanyProfileRepository;
import com.dennis.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    EmailClient emailClient;

    public User registerUser(UserRegistrationRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(hashPassword(request.getPassword()));  // Implement password hashing
        user.setContact(request.getContact());
        user.setRole(request.getRole());
        user.setStatus(Status.INACTIVE);

        // Generate OTP
        String otp = generateOtp();
        user.setOtp(otp);
        user.setOtpExpiration(LocalDateTime.now().plusMinutes(10));

        userRepository.save(user);

        // Send OTP email
        emailClient.sendOtp(user.getEmail(), otp);

        return user;
    }

    public CompanyProfile registerCompany(CompanyRegistrationRequest request) throws IOException {
        User user = registerUser(new UserRegistrationRequest(
                request.getName(), request.getEmail(), request.getPassword(), request.getContact(), Role.COMPANY));

        CompanyProfile profile = new CompanyProfile();
        profile.setUser(user);
        profile.setWebsite(request.getWebsite());

        if (request.getCertificate() != null && !request.getCertificate().isEmpty()) {
            profile.setRegistrationCertificate(request.getCertificate().getBytes());
        }
        else{
            profile.setRegistrationCertificate(null);
        }

        if (request.getLogo() != null && !request.getLogo().isEmpty()) {
            profile.setLogo(request.getLogo().getBytes());
        }
        else{
            profile.setLogo(null);
        }

        return companyProfileRepository.save(profile);
    }

    public boolean confirmOtp(String email, String otp) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getOtp().equals(otp) && user.getOtpExpiration().isAfter(LocalDateTime.now())) {
                if (user.getRole().equals(Role.COMPANY)) {
                    user.setStatus(Status.PENDING);
                } else {
                    user.setStatus(Status.APPROVED);
                }
                user.setOtp(null);  // Clear OTP
                user.setOtpExpiration(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

//    public List<CompanyProfile> getPendingCompanyRegistrations() {
//        return companyProfileRepository.findByUser_Status(Status.PENDING);
//    }


    public List<User> getPendingCompanyRegistrations() {
        return userRepository.findByStatus(Status.PENDING);
    }

    @Transactional
    public List<CompanyProfileResponse> getPendingCompanyProfiles() {
        List<CompanyProfile> pendingProfiles = companyProfileRepository.findByUser_Status(Status.PENDING);

        // Map the list of CompanyProfile to CompanyProfileResponse
        return pendingProfiles.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    private CompanyProfileResponse mapToResponse(CompanyProfile profile) {
        // Encode the registrationCertificate and logo to Base64 strings
        String encodedCertificate = profile.getRegistrationCertificate() != null
                ? Base64.getEncoder().encodeToString(profile.getRegistrationCertificate())
                : null; // Or use "" or a default string

        String encodedLogo = profile.getLogo() != null
                ? Base64.getEncoder().encodeToString(profile.getLogo())
                : null; // Or use "" or a default string

        User user = profile.getUser();

        // Create and return the response
        return new CompanyProfileResponse(
                user.getName(),
                user.getEmail(),
                user.getContact(),
                profile.getWebsite(),
                encodedCertificate,
                encodedLogo
        );
    }

    public void approveOrRejectCompany(AdminApprovalRequest request) {
        Optional<User> companyOptional = userRepository.findByEmail(request.getEmail());
        if (companyOptional.isPresent()) {
            //CompanyProfile company = companyOptional.get();
            //User user = company.getUser();

            //###check if user has verified before approving###
            companyOptional.get().setStatus(request.getStatus());
            userRepository.save(companyOptional.get());

            // Send notification
            String email = companyOptional.get().getEmail();
            if (request.getStatus() == Status.APPROVED) {
                emailClient.sendApprovalEmail(email);
            } else {
                emailClient.sendRejectionEmail(email);
            }
        }
    }


    private String generateOtp() {
        // Implement OTP generation logic
        return UUID.randomUUID().toString().substring(0, 6);
    }

    private String hashPassword(String password) {
        // Implement password hashing (e.g., BCrypt)
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}

