package com.smartjobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String role; // "candidate", "company", "admin"
    
    // For company registration
    private String companyName;
    private String industry;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public SignupRequest() {}

    public SignupRequest(String name, String email, String password, String role, String companyName, String industry) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.companyName = companyName;
        this.industry = industry;
    }

    public static SignupRequestBuilder builder() { return new SignupRequestBuilder(); }

    public static class SignupRequestBuilder {
        private String name;
        private String email;
        private String password;
        private String role;
        private String companyName;
        private String industry;

        public SignupRequestBuilder name(String name) { this.name = name; return this; }
        public SignupRequestBuilder email(String email) { this.email = email; return this; }
        public SignupRequestBuilder password(String password) { this.password = password; return this; }
        public SignupRequestBuilder role(String role) { this.role = role; return this; }
        public SignupRequestBuilder companyName(String companyName) { this.companyName = companyName; return this; }
        public SignupRequestBuilder industry(String industry) { this.industry = industry; return this; }

        public SignupRequest build() {
            SignupRequest instance = new SignupRequest();
            instance.name = this.name;
            instance.email = this.email;
            instance.password = this.password;
            instance.role = this.role;
            instance.companyName = this.companyName;
            instance.industry = this.industry;
            return instance;
        }
    }
}
