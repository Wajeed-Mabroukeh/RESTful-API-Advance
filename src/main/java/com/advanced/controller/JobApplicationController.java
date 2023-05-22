package com.advanced.controller;

import com.advanced.model.JobApplication;
import com.advanced.model.JobListing;
import com.advanced.service.JobApplicationService;
import com.advanced.service.JobListingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-applications")
@Api(tags = "Job Applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @Autowired
    private JobListingService jobListingService;

    @GetMapping
    @ApiOperation("Retrieves all job applications.")
    @ApiResponse(code = 200, message = "Successfully retrieved")
    public List<JobApplication> findAll() {
        List<JobApplication> jobApplications = jobApplicationService.findAll();
        if (jobApplications.isEmpty()) {
            throw new IllegalArgumentException("No job applications exist.");
        }
        return jobApplications;
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieves a specific application by its ID.")
    @ApiResponse(code = 200, message = "Successfully retrieved")
    public JobApplication findById(
            @ApiParam(value = "Enter the application ID you want to GET", example = "5") @PathVariable Long id) {
        JobApplication jobApplication = jobApplicationService.findById(id);
        if (jobApplication == null) {
            throw new IllegalArgumentException("Job application with ID " + id + " does not exist.");
        }
        return jobApplication;
    }

    @PostMapping
    @ApiOperation("Creates a new job application.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public JobApplication save(
            @ApiParam(value = "Enter the application details you want to save (Job ID, Applicant Name, Applicant Email, Resume URL, and Cover Letter)") @RequestBody JobApplication jobApplication)
            throws IllegalArgumentException {

        if (jobApplication.getJobId() == null || jobApplication.getApplicantName() == null
                || jobApplication.getApplicantEmail() == null || jobApplication.getResumeUrl() == null
                || jobApplication.getCoverLetter() == null) {
            throw new NullPointerException("All details are required.");
        }

        JobListing list = jobListingService.findById(jobApplication.getJobId());

        if (list == null) {
            throw new IllegalArgumentException(
                    "Job listing with ID " + jobApplication.getJobId() + " does not exist.");
        }

        jobApplication.setJobListing(list);
        return jobApplicationService.save(jobApplication);

    }

    @PutMapping("/{id}")
    @ApiOperation("Updates an existing job application.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    public JobApplication update(
            @ApiParam(value = "Enter the application ID you want to update", example = "5") @PathVariable Long id,
            @ApiParam(value = "Enter the application details you want to update") @RequestBody JobApplication jobApplication) {

        if (jobApplication.getJobId() == null || jobApplication.getApplicantName() == null
                || jobApplication.getApplicantEmail() == null || jobApplication.getResumeUrl() == null
                || jobApplication.getCoverLetter() == null) {
            throw new NullPointerException("All details are required.");
        }

        if (jobApplicationService.findById(id) == null) {
            throw new IllegalArgumentException("Job application with ID " + id + " does not exist.");
        }

        JobListing list = jobListingService.findById(jobApplication.getJobId());
        if (list == null) {
            throw new IllegalArgumentException(
                    "Job listing with ID " + jobApplication.getJobId() + " does not exist.");
        }

        jobApplication.setJobListing(list);
        jobApplication.setId(id);
        return jobApplicationService.save(jobApplication);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Deletes a job application.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted", examples = @Example(value = {
                    @ExampleProperty(value = "{\n  \"message\": \"Job application with ID 5 was deleted.\"\n}", mediaType = "*/*")
            })),
            @ApiResponse(code = 404, message = "Not Found")
    })
    public HashMap<String, String> deleteById(
            @ApiParam(value = "Enter the application ID you want to delete", example = "5") @PathVariable Long id) {
        try {
            jobApplicationService.deleteById(id);
            HashMap<String, String> map = new HashMap<>();
            map.put("message", "Job application with ID " + id + " was deleted.");
            return map;
        } catch (Exception e) {
            throw new IllegalArgumentException("Job application with ID " + id + " does not exist.");
        }
    }
}
