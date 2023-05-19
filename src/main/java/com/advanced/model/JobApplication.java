package com.advanced.model;

import jakarta.persistence.*;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private JobListing jobListing;

    private String applicantName;
    private String applicantEmail;
    private String resumeUrl;
    private String coverLetter;

    public JobApplication() {
    }

    public JobApplication(JobListing jobListing, String applicantName, String applicantEmail, String resumeUrl, String coverLetter) {
        this.jobListing = jobListing;
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
}

