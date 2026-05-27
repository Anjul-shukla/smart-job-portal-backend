package com.smartjobportal.service;

import com.smartjobportal.dto.JwtResponse;
import com.smartjobportal.dto.LoginRequest;
import com.smartjobportal.dto.SignupRequest;
import com.smartjobportal.dto.TokenRefreshRequest;
import com.smartjobportal.dto.TokenRefreshResponse;

public interface AuthService {
    void registerUser(SignupRequest signUpRequest);
    JwtResponse authenticateUser(LoginRequest loginRequest);
    TokenRefreshResponse refreshToken(TokenRefreshRequest request);
    void logout(String email); // Simplified token blacklist simulation
}
