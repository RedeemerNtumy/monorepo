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


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @PostMapping("/company/register")
    public ResponseEntity<CompanyProfile> registerCompany(@ModelAttribute CompanyRegistrationRequest request) throws IOException {
        CompanyProfile profile = userService.registerCompany(request);
        return new ResponseEntity<>(profile, HttpStatus.CREATED);
    }

    @PostMapping("/talent/register")
    public ResponseEntity<User> registerTalent(@RequestBody UserRegistrationRequest request) {
        request.setRole(Role.TALENT);
        User user = userService.registerUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/confirm-otp")
    public ResponseEntity<String> confirmOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isConfirmed = userService.confirmOtp(email, otp);
        if (isConfirmed) {
            return new ResponseEntity<>("OTP confirmed, account activated.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid OTP or OTP expired.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent()) {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.get().getEmail());
            userDto.setPassword(user.get().getPassword());
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/company/pending-companies")
//    public ResponseEntity<List<User>> getPendingCompanies() {
//        List<User> pendingCompanies = userService.getPendingCompanyRegistrations();
//        return new ResponseEntity<>(pendingCompanies, HttpStatus.OK);
//    }

    @GetMapping("/company/pending-companies")
    public ResponseEntity<List<CompanyProfileResponse>> getPendingCompanies() {
        //return (ResponseEntity<List<CompanyProfileResponse>>) userService.getPendingCompanyProfiles();
        List<CompanyProfileResponse> profileResponses = userService.getPendingCompanyProfiles();
        return ResponseEntity.ok(profileResponses);
    }

    @PostMapping("/company/approve-company")
    public ResponseEntity<String> approveOrRejectCompany(@RequestBody AdminApprovalRequest request) {
        userService.approveOrRejectCompany(request);
        return new ResponseEntity<>("Company status updated.", HttpStatus.OK);
    }

}

