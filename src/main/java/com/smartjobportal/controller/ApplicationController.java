package com.smartjobportal.controller;

import com.smartjobportal.dto.ApplicationDto;
import com.smartjobportal.entity.ApplicationStatus;
import com.smartjobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply/{jobId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ApplicationDto> applyForJob(@PathVariable Long jobId, Authentication authentication) {
        return ResponseEntity.ok(applicationService.applyForJob(jobId, authentication.getName()));
    }

    @GetMapping("/applications/user")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<ApplicationDto>> getMyApplications(Authentication authentication) {
        return ResponseEntity.ok(applicationService.getApplicationsByUser(authentication.getName()));
    }

    @GetMapping("/applications/job/{jobId}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<List<ApplicationDto>> getApplicationsByJob(@PathVariable Long jobId, Authentication authentication) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId, authentication.getName()));
    }

    @PutMapping("/applications/{id}/status")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<ApplicationDto> updateApplicationStatus(
            @PathVariable Long id, 
            @RequestParam ApplicationStatus status, 
            Authentication authentication) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(id, status, authentication.getName()));
    }
}
