package com.smartjobportal.controller;

import com.smartjobportal.dto.MessageResponse;
import com.smartjobportal.dto.ProfileDto;
import com.smartjobportal.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<ProfileDto> getProfile(Authentication authentication) {
        return ResponseEntity.ok(profileService.getProfile(authentication.getName()));
    }

    @PutMapping
    public ResponseEntity<ProfileDto> updateProfile(Authentication authentication, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(authentication.getName(), profileDto));
    }

    @PostMapping("/resume")
    public ResponseEntity<ProfileDto> uploadResume(Authentication authentication, @RequestParam String resumeUrl) {
        return ResponseEntity.ok(profileService.uploadResume(authentication.getName(), resumeUrl));
    }
}
