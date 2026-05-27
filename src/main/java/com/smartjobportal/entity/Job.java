package com.smartjobportal.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    private String location;

    private BigDecimal salaryMin;
    private BigDecimal salaryMax;

    private Integer experienceLevel; // e.g., years of experience required

    // Storing skills as a comma-separated string for simplicity
    private String skillsRequired;

    private LocalDate expiryDate;
    
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public BigDecimal getSalaryMin() { return salaryMin; }
    public void setSalaryMin(BigDecimal salaryMin) { this.salaryMin = salaryMin; }

    public BigDecimal getSalaryMax() { return salaryMax; }
    public void setSalaryMax(BigDecimal salaryMax) { this.salaryMax = salaryMax; }

    public Integer getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(Integer experienceLevel) { this.experienceLevel = experienceLevel; }

    public String getSkillsRequired() { return skillsRequired; }
    public void setSkillsRequired(String skillsRequired) { this.skillsRequired = skillsRequired; }

    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Job() {}

    public Job(Long id, String title, String description, Company company, String location, BigDecimal salaryMin, BigDecimal salaryMax, Integer experienceLevel, String skillsRequired, LocalDate expiryDate, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.company = company;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.experienceLevel = experienceLevel;
        this.skillsRequired = skillsRequired;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
    }

    public static JobBuilder builder() { return new JobBuilder(); }

    public static class JobBuilder {
        private Long id;
        private String title;
        private String description;
        private Company company;
        private String location;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private Integer experienceLevel;
        private String skillsRequired;
        private LocalDate expiryDate;
        private LocalDateTime createdAt;

        public JobBuilder id(Long id) { this.id = id; return this; }
        public JobBuilder title(String title) { this.title = title; return this; }
        public JobBuilder description(String description) { this.description = description; return this; }
        public JobBuilder company(Company company) { this.company = company; return this; }
        public JobBuilder location(String location) { this.location = location; return this; }
        public JobBuilder salaryMin(BigDecimal salaryMin) { this.salaryMin = salaryMin; return this; }
        public JobBuilder salaryMax(BigDecimal salaryMax) { this.salaryMax = salaryMax; return this; }
        public JobBuilder experienceLevel(Integer experienceLevel) { this.experienceLevel = experienceLevel; return this; }
        public JobBuilder skillsRequired(String skillsRequired) { this.skillsRequired = skillsRequired; return this; }
        public JobBuilder expiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; return this; }
        public JobBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public Job build() {
            Job instance = new Job();
            instance.id = this.id;
            instance.title = this.title;
            instance.description = this.description;
            instance.company = this.company;
            instance.location = this.location;
            instance.salaryMin = this.salaryMin;
            instance.salaryMax = this.salaryMax;
            instance.experienceLevel = this.experienceLevel;
            instance.skillsRequired = this.skillsRequired;
            instance.expiryDate = this.expiryDate;
            instance.createdAt = this.createdAt;
            return instance;
        }
    }
}
