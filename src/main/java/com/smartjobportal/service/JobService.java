package com.smartjobportal.service;

import com.smartjobportal.dto.JobDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    JobDto createJob(String email, JobDto jobDto);
    JobDto updateJob(Long id, String email, JobDto jobDto);
    void deleteJob(Long id, String email);
    JobDto getJobById(Long id);
    Page<JobDto> getAllJobs(Pageable pageable);
    Page<JobDto> searchJobs(String location, String skills, Integer minExperience, Pageable pageable);
}
