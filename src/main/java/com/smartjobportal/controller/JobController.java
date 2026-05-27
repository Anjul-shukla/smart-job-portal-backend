package com.smartjobportal.controller;

import com.smartjobportal.dto.JobDto;
import com.smartjobportal.dto.MessageResponse;
import com.smartjobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<JobDto> createJob(Authentication authentication, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.createJob(authentication.getName(), jobDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<JobDto> updateJob(@PathVariable Long id, Authentication authentication, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.updateJob(id, authentication.getName(), jobDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<MessageResponse> deleteJob(@PathVariable Long id, Authentication authentication) {
        jobService.deleteJob(id, authentication.getName());
        return ResponseEntity.ok(new MessageResponse("Job deleted successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.getJobById(id));
    }

    @GetMapping
    public ResponseEntity<Page<JobDto>> getAllJobs(Pageable pageable) {
        return ResponseEntity.ok(jobService.getAllJobs(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<JobDto>> searchJobs(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String skills,
            @RequestParam(required = false) Integer minExperience,
            Pageable pageable) {
        return ResponseEntity.ok(jobService.searchJobs(location, skills, minExperience, pageable));
    }
}
