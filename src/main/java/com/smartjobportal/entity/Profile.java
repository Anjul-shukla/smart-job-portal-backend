package com.smartjobportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    // Comma-separated list of skills
    private String skills;

    // Years of experience
    private Integer experience;

    private String education;

    private String resumeUrl;

    public int calculateCompletionPercentage() {
        int score = 0;
        if (skills != null && !skills.trim().isEmpty()) score += 25;
        if (experience != null) score += 25;
        if (education != null && !education.trim().isEmpty()) score += 25;
        if (resumeUrl != null && !resumeUrl.trim().isEmpty()) score += 25;
        return score;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public Integer getExperience() { return experience; }
    public void setExperience(Integer experience) { this.experience = experience; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }

    public Profile() {}

    public Profile(Long id, User user, String skills, Integer experience, String education, String resumeUrl) {
        this.id = id;
        this.user = user;
        this.skills = skills;
        this.experience = experience;
        this.education = education;
        this.resumeUrl = resumeUrl;
    }

    public static ProfileBuilder builder() { return new ProfileBuilder(); }

    public static class ProfileBuilder {
        private Long id;
        private User user;
        private String skills;
        private Integer experience;
        private String education;
        private String resumeUrl;

        public ProfileBuilder id(Long id) { this.id = id; return this; }
        public ProfileBuilder user(User user) { this.user = user; return this; }
        public ProfileBuilder skills(String skills) { this.skills = skills; return this; }
        public ProfileBuilder experience(Integer experience) { this.experience = experience; return this; }
        public ProfileBuilder education(String education) { this.education = education; return this; }
        public ProfileBuilder resumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; return this; }

        public Profile build() {
            Profile instance = new Profile();
            instance.id = this.id;
            instance.user = this.user;
            instance.skills = this.skills;
            instance.experience = this.experience;
            instance.education = this.education;
            instance.resumeUrl = this.resumeUrl;
            return instance;
        }
    }
}
