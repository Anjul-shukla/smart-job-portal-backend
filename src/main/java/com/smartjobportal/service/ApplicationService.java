package com.smartjobportal.service;

import com.smartjobportal.dto.ApplicationDto;
import com.smartjobportal.entity.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    ApplicationDto applyForJob(Long jobId, String email);
    List<ApplicationDto> getApplicationsByUser(String email);
    List<ApplicationDto> getApplicationsByJob(Long jobId, String email);
    ApplicationDto updateApplicationStatus(Long applicationId, ApplicationStatus status, String email);
}
