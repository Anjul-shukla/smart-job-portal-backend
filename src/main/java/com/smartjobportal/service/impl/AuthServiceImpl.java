package com.smartjobportal.service.impl;

import com.smartjobportal.dto.JwtResponse;
import com.smartjobportal.dto.LoginRequest;
import com.smartjobportal.dto.SignupRequest;
import com.smartjobportal.dto.TokenRefreshRequest;
import com.smartjobportal.dto.TokenRefreshResponse;
import com.smartjobportal.entity.Company;
import com.smartjobportal.entity.Role;
import com.smartjobportal.entity.User;
import com.smartjobportal.exception.BadRequestException;
import com.smartjobportal.repository.CompanyRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.security.JwtUtils;
import com.smartjobportal.security.UserDetailsImpl;
import com.smartjobportal.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    @Transactional
    public void registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Error: Email is already in use!");
        }

        Role userRole;
        if ("company".equalsIgnoreCase(signUpRequest.getRole())) {
            userRole = Role.ROLE_COMPANY;
        } else if ("admin".equalsIgnoreCase(signUpRequest.getRole())) {
            userRole = Role.ROLE_ADMIN;
        } else {
            userRole = Role.ROLE_CANDIDATE;
        }

        User user = User.builder()
                .name(signUpRequest.getName())
                .email(signUpRequest.getEmail())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(userRole)
                .build();

        userRepository.save(user);

        if (userRole == Role.ROLE_COMPANY) {
            Company company = Company.builder()
                    .name(signUpRequest.getCompanyName() != null ? signUpRequest.getCompanyName() : signUpRequest.getName())
                    .industry(signUpRequest.getIndustry())
                    .build();
            companyRepository.save(company);
        }
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefreshToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return new JwtResponse(jwt, refreshToken, userDetails.getId(), userDetails.getEmail(), userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Override
    public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        if (jwtUtils.validateJwtToken(requestRefreshToken)) {
            String email = jwtUtils.getUserNameFromJwtToken(requestRefreshToken);
            String token = jwtUtils.generateTokenFromUsername(email);
            return new TokenRefreshResponse(token, requestRefreshToken);
        } else {
            throw new BadRequestException("Refresh token is invalid or expired");
        }
    }

    @Override
    public void logout(String email) {
        // Simulated token blacklist, in a real scenario you would invalidate the token in DB/Redis
    }
}
