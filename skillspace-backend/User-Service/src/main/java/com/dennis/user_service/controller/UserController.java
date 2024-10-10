package com.dennis.user_service.controller;

import com.dennis.user_service.dto.*;
import com.dennis.user_service.model.CompanyProfile;
import com.dennis.user_service.model.Role;
import com.dennis.user_service.model.User;
import com.dennis.user_service.repository.CompanyProfileRepository;
import com.dennis.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/company/register")
    public ResponseEntity<String> registerCompany(@ModelAttribute CompanyRegistrationRequest request) throws IOException {
        userService.registerCompany(request);
        return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
    }

    @PostMapping("/talent/register")
    public ResponseEntity<String> registerTalent(@RequestBody UserRegistrationRequest request) {
        request.setRole(Role.TALENT);
        userService.registerUser(request);
        return new ResponseEntity<>("Talent Created", HttpStatus.CREATED);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ChangePassRequest request) {
        userService.resetPassword(request);
        return new ResponseEntity<>("Reset password successful", HttpStatus.CREATED);
    }



    @PostMapping("/confirm-otp")
    public ResponseEntity<String> confirmOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isConfirmed = userService.confirmOtp(email, otp);
        if (isConfirmed) {
            return new ResponseEntity<>("OTP confirmed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired OTP.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/password-reset/confirm-otp")
    public ResponseEntity<String> confirmPasswordResetOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isConfirmed = userService.confirmPasswordResetOtp(email, otp);
        if (isConfirmed) {
            return new ResponseEntity<>("OTP confirmed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired OTP.", HttpStatus.BAD_REQUEST);
        }
    }

    //generate and send otp
    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateAndSendOtp(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            userService.generateAndSendOtp(user.get());
            return new ResponseEntity<>("OTP generated", HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }


    }


    @GetMapping("/talent/email/{email}")
    public ResponseEntity<UserDto> getTalentByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setEmail(user.get().getEmail());
            userDto.setName(user.get().getName());
            userDto.setContact(user.get().getContact());
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/company/email/{email}")
    public ResponseEntity<CompanyProfileResponse> getCompanyEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            CompanyProfileResponse companyProfileResponse = new CompanyProfileResponse();
            companyProfileResponse.setEmail(user.get().getEmail());
            companyProfileResponse.setName(user.get().getName());
            companyProfileResponse.setContact(user.get().getContact());
            return ResponseEntity.ok(companyProfileResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthResponse> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setUsername(user.get().getEmail());
            authResponse.setPassword(user.get().getPassword());
            return ResponseEntity.ok(authResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/role/{email}")
    public ResponseEntity<Role> getUserRoleByEmail(@PathVariable String email) {
        Optional<Role> role = userService.findRoleByEmail(email);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/company/pending-companies")
    public ResponseEntity<List<CompanyProfileResponse>> getPendingCompanies() {
        List<CompanyProfileResponse> profileResponses = userService.getPendingCompanyProfiles();
        return ResponseEntity.ok(profileResponses);
    }

    @PostMapping("/company/approve-company")
    public ResponseEntity<String> approveOrRejectCompany(@RequestBody AdminApprovalRequest request) {
        userService.approveOrRejectCompany(request);
        return new ResponseEntity<>("Company status updated.", HttpStatus.OK);
    }

}

