package com.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.advanced.model.JobListing;
import com.advanced.service.JobListingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

@RestController
@RequestMapping("/api/job-listings")
@Api(tags = "Job Listings")
public class JobListingController {
    @Autowired
    private JobListingService jobListingService;

    @ApiOperation("Retrieves all job listings.")
    @ApiResponse(code = 200, message = "Successfully retrieved")
    @GetMapping
    public List<JobListing> findAll() {
        List<JobListing> jobListings = jobListingService.findAll();
        if (jobListings.isEmpty()) {
            throw new IllegalArgumentException("No job listings exist.");
        }
        return jobListings;
    }

    @ApiOperation("Retrieves a specific job listing by its ID.")
    @ApiResponse(code = 200, message = "Successfully retrieved")
    @GetMapping("/{id}")
    public JobListing findById(
            @ApiParam(value = "Enter the job ID you want to GET", example = "5") @PathVariable Long id) {
        JobListing jobListing = jobListingService.findById(id);
        if (jobListing == null) {
            throw new IllegalArgumentException("Job listing with ID " + id + " does not exist.");
        }
        return jobListing;
    }

    @ApiOperation("Creates a new job listing.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping
    public JobListing save(
            @ApiParam(value = "Enter the job details you want to save (Job Title, Description, Location, Requirements and Salary Range)") @RequestBody JobListing jobListing) {
        if (jobListing.getJobTitle() == null || jobListing.getDescription() == null ||
                jobListing.getLocation() == null || jobListing.getRequirements() == null
                || jobListing.getSalaryRange() == null) {
            throw new NullPointerException("Job listing details are required.");
        }

        return jobListingService.save(jobListing);
    }

    @ApiOperation("Updates an existing job listing.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PutMapping("/{id}")
    public JobListing update(
            @ApiParam(value = "Enter the job ID you want to update", example = "5") @PathVariable Long id,
            @ApiParam(value = "Enter the job details you want to update") @RequestBody JobListing jobListing) {

        if (jobListing.getJobTitle() == null || jobListing.getDescription() == null ||
                jobListing.getLocation() == null || jobListing.getRequirements() == null
                || jobListing.getSalaryRange() == null) {
            throw new NullPointerException("Job listing details are required.");
        }

        if (jobListingService.findById(id) == null) {
            throw new IllegalArgumentException("Job listing with ID " + id + " does not exist.");
        }

        jobListing.setId(id);
        return jobListingService.save(jobListing);
    }

    @ApiOperation("Deletes a job listing.")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted", examples = @Example(value = {
                @ExampleProperty(value = "Job listing with ID 5 was deleted.", mediaType = "*/*")
        })),
        @ApiResponse(code = 404, message = "Not Found")
})
    @DeleteMapping("/{id}")
    public String deleteById(
            @ApiParam(value = "Enter the job ID you want to delete", example = "5") @PathVariable Long id) {
        try {
            jobListingService.deleteById(id);
            return "Job listing with ID " + id + " was deleted.";
        } catch (Exception e) {
            throw new IllegalArgumentException("Job listing with ID " + id + " does not exist.");
        }
    }

    @ApiOperation("Search job listings.")
    @ApiResponse(code = 200, message = "Successfully retrieved")
    @GetMapping("/search")
    public List<JobListing> search(
            @ApiParam("Search for a job by title") @RequestParam(required = false) String jobTitle,
            @ApiParam("Search for a job by location") @RequestParam(required = false) String location,
            @ApiParam("Search for a job by salary range") @RequestParam(required = false) String salaryRange) {
        List<JobListing> jobListings = jobListingService.search(jobTitle, location, salaryRange);
        if (jobListings.isEmpty()) {
            throw new IllegalArgumentException("There are no jobs according to the entered search criteria.");
        }
        return jobListings;
    }
}