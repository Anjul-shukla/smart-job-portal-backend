package com.smartjobportal.controller;

import com.smartjobportal.dto.JwtResponse;
import com.smartjobportal.dto.LoginRequest;
import com.smartjobportal.dto.MessageResponse;
import com.smartjobportal.dto.SignupRequest;
import com.smartjobportal.dto.TokenRefreshRequest;
import com.smartjobportal.dto.TokenRefreshResponse;
import com.smartjobportal.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logoutUser(Authentication authentication) {
        if(authentication != null) {
            authService.logout(authentication.getName());
        }
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}
