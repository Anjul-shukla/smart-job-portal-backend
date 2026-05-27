package com.smartjobportal.controller;

import com.smartjobportal.dto.JobDto;
import com.smartjobportal.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<List<JobDto>> getJobRecommendations(Authentication authentication) {
        return ResponseEntity.ok(recommendationService.getRecommendedJobsForCandidate(authentication.getName()));
    }
}
