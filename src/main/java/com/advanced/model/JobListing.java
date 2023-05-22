package com.advanced.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class JobListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "Job ID", example = "5")
    private Long id;

    @ApiModelProperty(value = "Job Title", example = "Software Engineer")
    private String jobTitle;

    @ApiModelProperty(value = "Job Description", example = "We are looking for a software engineer to join our team.")
    private String description;

    @ApiModelProperty(value = "Job Requirements", example = "Must have 5 years of experience in Java.")
    private String requirements;

    @ApiModelProperty(value = "Job Location", example = "Nablus")
    private String location;

    @ApiModelProperty(value = "Salary Range", example = "100,000 NIS - 120,000 NIS")
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