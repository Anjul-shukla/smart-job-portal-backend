package com.smartjobportal.dto;


import java.time.LocalDateTime;

public class ApplicationDto {
    private Long id;
    private Long jobId;
    private String jobTitle;
    private String companyName;
    private Long userId;
    private String candidateName;
    private String candidateEmail;
    private String status;
    private LocalDateTime appliedDate;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getJobId() { return jobId; }
    public void setJobId(Long jobId) { this.jobId = jobId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getCandidateEmail() { return candidateEmail; }
    public void setCandidateEmail(String candidateEmail) { this.candidateEmail = candidateEmail; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }

    public ApplicationDto() {}

    public ApplicationDto(Long id, Long jobId, String jobTitle, String companyName, Long userId, String candidateName, String candidateEmail, String status, LocalDateTime appliedDate) {
        this.id = id;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.userId = userId;
        this.candidateName = candidateName;
        this.candidateEmail = candidateEmail;
        this.status = status;
        this.appliedDate = appliedDate;
    }

    public static ApplicationDtoBuilder builder() { return new ApplicationDtoBuilder(); }

    public static class ApplicationDtoBuilder {
        private Long id;
        private Long jobId;
        private String jobTitle;
        private String companyName;
        private Long userId;
        private String candidateName;
        private String candidateEmail;
        private String status;
        private LocalDateTime appliedDate;

        public ApplicationDtoBuilder id(Long id) { this.id = id; return this; }
        public ApplicationDtoBuilder jobId(Long jobId) { this.jobId = jobId; return this; }
        public ApplicationDtoBuilder jobTitle(String jobTitle) { this.jobTitle = jobTitle; return this; }
        public ApplicationDtoBuilder companyName(String companyName) { this.companyName = companyName; return this; }
        public ApplicationDtoBuilder userId(Long userId) { this.userId = userId; return this; }
        public ApplicationDtoBuilder candidateName(String candidateName) { this.candidateName = candidateName; return this; }
        public ApplicationDtoBuilder candidateEmail(String candidateEmail) { this.candidateEmail = candidateEmail; return this; }
        public ApplicationDtoBuilder status(String status) { this.status = status; return this; }
        public ApplicationDtoBuilder appliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; return this; }

        public ApplicationDto build() {
            ApplicationDto instance = new ApplicationDto();
            instance.id = this.id;
            instance.jobId = this.jobId;
            instance.jobTitle = this.jobTitle;
            instance.companyName = this.companyName;
            instance.userId = this.userId;
            instance.candidateName = this.candidateName;
            instance.candidateEmail = this.candidateEmail;
            instance.status = this.status;
            instance.appliedDate = this.appliedDate;
            return instance;
        }
    }
}
