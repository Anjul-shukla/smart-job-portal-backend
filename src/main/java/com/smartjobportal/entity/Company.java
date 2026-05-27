package com.smartjobportal.entity;

import jakarta.persistence.*;
// Removed Lombok

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String industry;
    
    @Column(length = 1000)
    private String description;

    public Company() {}

    public Company(Long id, String name, String industry, String description) {
        this.id = id;
        this.name = name;
        this.industry = industry;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public static CompanyBuilder builder() {
        return new CompanyBuilder();
    }

    public static class CompanyBuilder {
        private Long id;
        private String name;
        private String industry;
        private String description;

        public CompanyBuilder id(Long id) { this.id = id; return this; }
        public CompanyBuilder name(String name) { this.name = name; return this; }
        public CompanyBuilder industry(String industry) { this.industry = industry; return this; }
        public CompanyBuilder description(String description) { this.description = description; return this; }

        public Company build() {
            return new Company(id, name, industry, description);
        }
    }
}
