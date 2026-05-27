package com.smartjobportal.service.impl;

import com.smartjobportal.dto.JobDto;
import com.smartjobportal.dto.ProfileDto;
import com.smartjobportal.service.JobService;
import com.smartjobportal.service.ProfileService;
import com.smartjobportal.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private JobService jobService;

    @Override
    public List<JobDto> getRecommendedJobsForCandidate(String email) {
        ProfileDto profile = profileService.getProfile(email);

        // Get all active jobs (for production, you should query with basic filters first to reduce load)
        List<JobDto> allJobs = jobService.getAllJobs(PageRequest.of(0, 100)).getContent();

        // Calculate score for each job, filter low scores, and sort by highest score
        return allJobs.stream()
                .map(job -> new JobMatch(job, calculateMatchScore(profile, job)))
                .filter(jm -> jm.score > 20) // Only recommend jobs with >20% match
                .sorted(Comparator.comparingInt(JobMatch::getScore).reversed())
                .map(JobMatch::getJob)
                .limit(10) // Top 10 recommendations
                .collect(Collectors.toList());
    }

    @Override
    public int calculateMatchScore(ProfileDto profile, JobDto job) {
        int score = 0;

        // Skill Match (50%)
        if (profile.getSkills() != null && job.getSkillsRequired() != null) {
            String[] profileSkills = profile.getSkills().toLowerCase().split(",");
            String jobSkillsReq = job.getSkillsRequired().toLowerCase();
            int matchedSkills = 0;
            for (String skill : profileSkills) {
                if (jobSkillsReq.contains(skill.trim())) {
                    matchedSkills++;
                }
            }
            int totalRequiredSkills = job.getSkillsRequired().split(",").length;
            if(totalRequiredSkills > 0) {
                double skillScore = ((double) matchedSkills / totalRequiredSkills) * 50;
                score += Math.min(skillScore, 50); // Cap at 50
            }
        }

        // Experience Match (20%)
        if (profile.getExperience() != null && job.getExperienceLevel() != null) {
            if (profile.getExperience() >= job.getExperienceLevel()) {
                score += 20;
            } else if (profile.getExperience() >= job.getExperienceLevel() - 1) {
                score += 10; // Partial score for being close
            }
        }

        // Profile Completion (10%)
        score += (profile.getCompletionPercentage() / 100.0) * 10;

        // Education & Activity Match are simplified for this example

        return Math.min(score, 100);
    }

    private static class JobMatch {
        JobDto job;
        int score;

        JobMatch(JobDto job, int score) {
            this.job = job;
            this.score = score;
        }

        int getScore() {
            return score;
        }

        JobDto getJob() {
            return job;
        }
    }
}
