package com.smartjobportal.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String companyName;
    private Long companyId;
    private String location;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private Integer experienceLevel;
    private String skillsRequired;
    private LocalDate expiryDate;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }

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

    public JobDto() {}

    public JobDto(Long id, String title, String description, String companyName, Long companyId, String location, BigDecimal salaryMin, BigDecimal salaryMax, Integer experienceLevel, String skillsRequired, LocalDate expiryDate, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.companyId = companyId;
        this.location = location;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.experienceLevel = experienceLevel;
        this.skillsRequired = skillsRequired;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
    }

    public static JobDtoBuilder builder() { return new JobDtoBuilder(); }

    public static class JobDtoBuilder {
        private Long id;
        private String title;
        private String description;
        private String companyName;
        private Long companyId;
        private String location;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private Integer experienceLevel;
        private String skillsRequired;
        private LocalDate expiryDate;
        private LocalDateTime createdAt;

        public JobDtoBuilder id(Long id) { this.id = id; return this; }
        public JobDtoBuilder title(String title) { this.title = title; return this; }
        public JobDtoBuilder description(String description) { this.description = description; return this; }
        public JobDtoBuilder companyName(String companyName) { this.companyName = companyName; return this; }
        public JobDtoBuilder companyId(Long companyId) { this.companyId = companyId; return this; }
        public JobDtoBuilder location(String location) { this.location = location; return this; }
        public JobDtoBuilder salaryMin(BigDecimal salaryMin) { this.salaryMin = salaryMin; return this; }
        public JobDtoBuilder salaryMax(BigDecimal salaryMax) { this.salaryMax = salaryMax; return this; }
        public JobDtoBuilder experienceLevel(Integer experienceLevel) { this.experienceLevel = experienceLevel; return this; }
        public JobDtoBuilder skillsRequired(String skillsRequired) { this.skillsRequired = skillsRequired; return this; }
        public JobDtoBuilder expiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; return this; }
        public JobDtoBuilder createdAt(LocalDateTime createdAt) { this.createdAt = createdAt; return this; }

        public JobDto build() {
            JobDto instance = new JobDto();
            instance.id = this.id;
            instance.title = this.title;
            instance.description = this.description;
            instance.companyName = this.companyName;
            instance.companyId = this.companyId;
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
