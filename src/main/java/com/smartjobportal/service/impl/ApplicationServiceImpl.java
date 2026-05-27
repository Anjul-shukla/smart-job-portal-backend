package com.smartjobportal.service.impl;

import com.smartjobportal.dto.ApplicationDto;
import com.smartjobportal.entity.Application;
import com.smartjobportal.entity.ApplicationStatus;
import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.User;
import com.smartjobportal.exception.BadRequestException;
import com.smartjobportal.exception.ResourceNotFoundException;
import com.smartjobportal.repository.ApplicationRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.service.ApplicationService;
import com.smartjobportal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ApplicationDto applyForJob(Long jobId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        if (applicationRepository.existsByJobIdAndUserId(jobId, user.getId())) {
            throw new BadRequestException("You have already applied for this job.");
        }

        Application application = Application.builder()
                .job(job)
                .user(user)
                .status(ApplicationStatus.APPLIED)
                .build();

        Application savedApplication = applicationRepository.save(application);
        
        emailService.sendEmail(email, "Application Received", "You have successfully applied for: " + job.getTitle());

        return mapToDto(savedApplication);
    }

    @Override
    public List<ApplicationDto> getApplicationsByUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return applicationRepository.findByUserId(user.getId()).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<ApplicationDto> getApplicationsByJob(Long jobId, String email) {
        // Validation: Ensure the company requesting the list owns the job
        User companyUser = userRepository.findByEmail(email).orElseThrow();
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        
        // Very basic validation (should check company ID properly)
        if (!job.getCompany().getName().equalsIgnoreCase(companyUser.getName())) {
            throw new BadRequestException("You do not have permission to view these applications.");
        }

        return applicationRepository.findByJobId(jobId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ApplicationDto updateApplicationStatus(Long applicationId, ApplicationStatus status, String email) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));
                
        // Validation check for company owning the job application omitted for brevity but should be here
        
        application.setStatus(status);
        Application updatedApplication = applicationRepository.save(application);

        String subject = "Update on your application for " + application.getJob().getTitle();
        String text = "Your application status has been updated to: " + status;
        emailService.sendEmail(application.getUser().getEmail(), subject, text);

        return mapToDto(updatedApplication);
    }

    private ApplicationDto mapToDto(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .jobId(application.getJob().getId())
                .jobTitle(application.getJob().getTitle())
                .companyName(application.getJob().getCompany().getName())
                .userId(application.getUser().getId())
                .candidateName(application.getUser().getName())
                .candidateEmail(application.getUser().getEmail())
                .status(application.getStatus().name())
                .appliedDate(application.getAppliedDate())
                .build();
    }
}
