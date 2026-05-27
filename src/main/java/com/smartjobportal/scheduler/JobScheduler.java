package com.smartjobportal.scheduler;

import com.smartjobportal.entity.Job;
import com.smartjobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class JobScheduler {

    @Autowired
    private JobRepository jobRepository;

    // Run every day at midnight
    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredJobs() {
        System.out.println("Scheduler running: Checking for expired jobs...");
        List<Job> expiredJobs = jobRepository.findByExpiryDateBefore(LocalDate.now());
        if (!expiredJobs.isEmpty()) {
            jobRepository.deleteAll(expiredJobs);
            System.out.println("Deleted " + expiredJobs.size() + " expired jobs.");
        }
    }

    // Run every Sunday at midnight to send reports (dummy implementation)
    @Scheduled(cron = "0 0 0 ? * SUN")
    public void sendWeeklyReports() {
        System.out.println("Scheduler running: Sending weekly reports to companies...");
        // Logic to compile stats and email companies would go here
    }
}
