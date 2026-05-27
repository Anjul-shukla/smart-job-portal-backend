package com.smartjobportal.service.impl;

import com.smartjobportal.dto.JobDto;
import com.smartjobportal.entity.Company;
import com.smartjobportal.entity.Job;
import com.smartjobportal.entity.User;
import com.smartjobportal.exception.BadRequestException;
import com.smartjobportal.exception.ResourceNotFoundException;
import com.smartjobportal.repository.CompanyRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @CacheEvict(value = "jobs", allEntries = true)
    public JobDto createJob(String email, JobDto jobDto) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // Assuming company name matches user name for simplified mapping, or one user handles one company
        // For production, a proper User-Company relation should be used.
        Company company = companyRepository.findAll().stream()
                .filter(c -> c.getName().equalsIgnoreCase(user.getName()))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Company profile not found for user"));

        Job job = Job.builder()
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .company(company)
                .location(jobDto.getLocation())
                .salaryMin(jobDto.getSalaryMin())
                .salaryMax(jobDto.getSalaryMax())
                .experienceLevel(jobDto.getExperienceLevel())
                .skillsRequired(jobDto.getSkillsRequired())
                .expiryDate(jobDto.getExpiryDate())
                .build();

        Job savedJob = jobRepository.save(job);
        return mapToDto(savedJob);
    }

    @Override
    @CacheEvict(value = "jobs", allEntries = true)
    public JobDto updateJob(Long id, String email, JobDto jobDto) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        
        job.setTitle(jobDto.getTitle());
        job.setDescription(jobDto.getDescription());
        job.setLocation(jobDto.getLocation());
        job.setSalaryMin(jobDto.getSalaryMin());
        job.setSalaryMax(jobDto.getSalaryMax());
        job.setExperienceLevel(jobDto.getExperienceLevel());
        job.setSkillsRequired(jobDto.getSkillsRequired());
        job.setExpiryDate(jobDto.getExpiryDate());

        Job updatedJob = jobRepository.save(job);
        return mapToDto(updatedJob);
    }

    @Override
    @CacheEvict(value = "jobs", allEntries = true)
    public void deleteJob(Long id, String email) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        jobRepository.delete(job);
    }

    @Override
    @Cacheable(value = "jobs", key = "#id")
    public JobDto getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return mapToDto(job);
    }

    @Override
    @Cacheable(value = "jobs", key = "'all_' + #pageable.pageNumber + '_' + #pageable.pageSize")
    public Page<JobDto> getAllJobs(Pageable pageable) {
        return jobRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public Page<JobDto> searchJobs(String location, String skills, Integer minExperience, Pageable pageable) {
        Specification<Job> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (location != null && !location.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%"));
            }
            if (skills != null && !skills.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("skillsRequired")), "%" + skills.toLowerCase() + "%"));
            }
            if (minExperience != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("experienceLevel"), minExperience));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return jobRepository.findAll(spec, pageable).map(this::mapToDto);
    }

    private JobDto mapToDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .companyName(job.getCompany().getName())
                .companyId(job.getCompany().getId())
                .location(job.getLocation())
                .salaryMin(job.getSalaryMin())
                .salaryMax(job.getSalaryMax())
                .experienceLevel(job.getExperienceLevel())
                .skillsRequired(job.getSkillsRequired())
                .expiryDate(job.getExpiryDate())
                .createdAt(job.getCreatedAt())
                .build();
    }
}
