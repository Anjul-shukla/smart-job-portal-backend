package com.smartjobportal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"job_id", "user_id"})
})
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApplicationStatus status;

    private LocalDateTime appliedDate;

    @PrePersist
    protected void onCreate() {
        appliedDate = LocalDateTime.now();
        if (status == null) {
            status = ApplicationStatus.APPLIED;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }

    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }

    public Application() {}

    public Application(Long id, Job job, User user, ApplicationStatus status, LocalDateTime appliedDate) {
        this.id = id;
        this.job = job;
        this.user = user;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public static ApplicationBuilder builder() { return new ApplicationBuilder(); }

    public static class ApplicationBuilder {
        private Long id;
        private Job job;
        private User user;
        private ApplicationStatus status;
        private LocalDateTime appliedDate;

        public ApplicationBuilder id(Long id) { this.id = id; return this; }
        public ApplicationBuilder job(Job job) { this.job = job; return this; }
        public ApplicationBuilder user(User user) { this.user = user; return this; }
        public ApplicationBuilder status(ApplicationStatus status) { this.status = status; return this; }
        public ApplicationBuilder appliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; return this; }

        public Application build() {
            Application instance = new Application();
            instance.id = this.id;
            instance.job = this.job;
            instance.user = this.user;
            instance.status = this.status;
            instance.appliedDate = this.appliedDate;
            return instance;
        }
    }
}
