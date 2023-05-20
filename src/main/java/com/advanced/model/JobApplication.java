package com.advanced.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;


@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(value = "Job ID", example = "5")
    private Long id;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private JobListing jobListing;
    
    @ApiModelProperty(value = "Job ID", example = "5")
    private Long jobId;

    @ApiModelProperty(value = "Applicant Name", example = "Wajeed Mabroukeh")
    private String applicantName;

    @ApiModelProperty(value = "Applicant Email", example = "wajeed.mabroukeh@gmail.com")
    private String applicantEmail;

    @ApiModelProperty(value = "Resume URL", example = "https://www.linkedin.com/in/wajeed-mabroukeh")
    private String resumeUrl;

    @ApiModelProperty(value = "Cover Letter", example = "I am a software engineer with 5 years of experience.")
    private String coverLetter;

    public JobApplication() {
    }

    public JobApplication(Long jobId, String applicantName, String applicantEmail, String resumeUrl, String coverLetter) {
        this.jobId = jobId;
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.resumeUrl = resumeUrl;
        this.coverLetter = coverLetter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}

