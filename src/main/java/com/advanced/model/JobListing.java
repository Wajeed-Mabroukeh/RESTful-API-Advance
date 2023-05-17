package com.advanced.model;

import jakarta.persistence.*;

@Entity
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String description;
    private String requirements;
    private String location;
    private String salaryRange;

    public JobListing() {
    }

    public JobListing(String jobTitle, String description, String requirements, String location, String salaryRange) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.requirements = requirements;
        this.location = location;
        this.salaryRange = salaryRange;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }
}
