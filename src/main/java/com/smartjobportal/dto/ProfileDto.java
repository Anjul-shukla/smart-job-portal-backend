package com.smartjobportal.dto;


public class ProfileDto {
    private Long id;
    private Long userId;
    private String userName;
    private String email;
    private String skills;
    private Integer experience;
    private String education;
    private String resumeUrl;
    private int completionPercentage;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }

    public int getCompletionPercentage() { return completionPercentage; }
    public void setCompletionPercentage(int completionPercentage) { this.completionPercentage = completionPercentage; }

    public ProfileDto() {}

    public ProfileDto(Long id, Long userId, String userName, String email, String skills, Integer experience, String education, String resumeUrl, int completionPercentage) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
        this.resumeUrl = resumeUrl;
        this.completionPercentage = completionPercentage;
    }

    public static ProfileDtoBuilder builder() { return new ProfileDtoBuilder(); }

    public static class ProfileDtoBuilder {
        private Long id;
        private Long userId;
        private String userName;
        private String email;
        private String skills;
        private Integer experience;
        private String education;
        private String resumeUrl;
        private int completionPercentage;

        public ProfileDtoBuilder id(Long id) { this.id = id; return this; }
        public ProfileDtoBuilder userId(Long userId) { this.userId = userId; return this; }
        public ProfileDtoBuilder userName(String userName) { this.userName = userName; return this; }
        public ProfileDtoBuilder email(String email) { this.email = email; return this; }
        public ProfileDtoBuilder skills(String skills) { this.skills = skills; return this; }
        public ProfileDtoBuilder experience(Integer experience) { this.experience = experience; return this; }
        public ProfileDtoBuilder education(String education) { this.education = education; return this; }
        public ProfileDtoBuilder resumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; return this; }
        public ProfileDtoBuilder completionPercentage(int completionPercentage) { this.completionPercentage = completionPercentage; return this; }

        public ProfileDto build() {
            ProfileDto instance = new ProfileDto();
            instance.id = this.id;
            instance.userId = this.userId;
            instance.userName = this.userName;
            instance.email = this.email;
            instance.skills = this.skills;
            instance.experience = this.experience;
            instance.education = this.education;
            instance.resumeUrl = this.resumeUrl;
            instance.completionPercentage = this.completionPercentage;
            return instance;
        }
    }
}
