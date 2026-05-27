package com.smartjobportal.service.impl;

import com.smartjobportal.dto.AdminStatsDto;
import com.smartjobportal.entity.ApplicationStatus;
import com.smartjobportal.repository.ApplicationRepository;
import com.smartjobportal.repository.JobRepository;
import com.smartjobportal.repository.UserRepository;
import com.smartjobportal.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public AdminStatsDto getPlatformStats() {
        long totalUsers = userRepository.count();
        long totalJobs = jobRepository.count();
        long totalApplications = applicationRepository.count();

        long hiredApplications = applicationRepository.findAll().stream()
                .filter(a -> a.getStatus() == ApplicationStatus.HIRED)
                .count();

        double conversionRate = totalApplications == 0 ? 0 : ((double) hiredApplications / totalApplications) * 100;

        // Simplified placeholder logic for most demanded skills and hiring activity
        Map<String, Long> dummySkills = new HashMap<>();
        dummySkills.put("Java", 50L);
        dummySkills.put("Spring Boot", 45L);
        dummySkills.put("React", 30L);

        Map<String, Long> dummyActivity = new HashMap<>();
        dummyActivity.put("Tech Corp", 12L);
        dummyActivity.put("Innovate Inc", 8L);

        return AdminStatsDto.builder()
                .totalUsers(totalUsers)
                .totalJobs(totalJobs)
                .totalApplications(totalApplications)
                .conversionRate(conversionRate)
                .mostDemandedSkills(dummySkills)
                .companyHiringActivity(dummyActivity)
                .build();
    }
}
