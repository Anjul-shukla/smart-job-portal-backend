package com.smartjobportal.dto;


import java.util.Map;

public class AdminStatsDto {
    private long totalUsers;
    private long totalJobs;
    private long totalApplications;
    private double conversionRate;
    private Map<String, Long> mostDemandedSkills;
    private Map<String, Long> companyHiringActivity;

    public long getTotalUsers() { return totalUsers; }
    public void setTotalUsers(long totalUsers) { this.totalUsers = totalUsers; }

    public long getTotalJobs() { return totalJobs; }
    public void setTotalJobs(long totalJobs) { this.totalJobs = totalJobs; }

    public long getTotalApplications() { return totalApplications; }
    public void setTotalApplications(long totalApplications) { this.totalApplications = totalApplications; }

    public double getConversionRate() { return conversionRate; }
    public void setConversionRate(double conversionRate) { this.conversionRate = conversionRate; }

    public Map<String, Long> getMostDemandedSkills() { return mostDemandedSkills; }
    public void setMostDemandedSkills(Map<String, Long> mostDemandedSkills) { this.mostDemandedSkills = mostDemandedSkills; }

    public Map<String, Long> getCompanyHiringActivity() { return companyHiringActivity; }
    public void setCompanyHiringActivity(Map<String, Long> companyHiringActivity) { this.companyHiringActivity = companyHiringActivity; }

    public AdminStatsDto() {}

    public AdminStatsDto(long totalUsers, long totalJobs, long totalApplications, double conversionRate, Map<String, Long> mostDemandedSkills, Map<String, Long> companyHiringActivity) {
        this.totalUsers = totalUsers;
        this.totalJobs = totalJobs;
        this.totalApplications = totalApplications;
        this.conversionRate = conversionRate;
        this.mostDemandedSkills = mostDemandedSkills;
        this.companyHiringActivity = companyHiringActivity;
    }

    public static AdminStatsDtoBuilder builder() { return new AdminStatsDtoBuilder(); }

    public static class AdminStatsDtoBuilder {
        private long totalUsers;
        private long totalJobs;
        private long totalApplications;
        private double conversionRate;
        private Map<String, Long> mostDemandedSkills;
        private Map<String, Long> companyHiringActivity;

        public AdminStatsDtoBuilder totalUsers(long totalUsers) { this.totalUsers = totalUsers; return this; }
        public AdminStatsDtoBuilder totalJobs(long totalJobs) { this.totalJobs = totalJobs; return this; }
        public AdminStatsDtoBuilder totalApplications(long totalApplications) { this.totalApplications = totalApplications; return this; }
        public AdminStatsDtoBuilder conversionRate(double conversionRate) { this.conversionRate = conversionRate; return this; }
        public AdminStatsDtoBuilder mostDemandedSkills(Map<String, Long> mostDemandedSkills) { this.mostDemandedSkills = mostDemandedSkills; return this; }
        public AdminStatsDtoBuilder companyHiringActivity(Map<String, Long> companyHiringActivity) { this.companyHiringActivity = companyHiringActivity; return this; }

        public AdminStatsDto build() {
            AdminStatsDto instance = new AdminStatsDto();
            instance.totalUsers = this.totalUsers;
            instance.totalJobs = this.totalJobs;
            instance.totalApplications = this.totalApplications;
            instance.conversionRate = this.conversionRate;
            instance.mostDemandedSkills = this.mostDemandedSkills;
            instance.companyHiringActivity = this.companyHiringActivity;
            return instance;
        }
    }
}
