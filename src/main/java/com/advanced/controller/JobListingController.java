package com.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.advanced.model.JobListing;
import com.advanced.service.JobListingService;

@RestController
@RequestMapping("/api/job-listings")
public class JobListingController {
    @Autowired
    private JobListingService jobListingService;

    @GetMapping
    public List<JobListing> findAll() {
        return jobListingService.findAll();
    }

    @GetMapping("/{id}")
    public JobListing findById(@PathVariable Long id) {
        return jobListingService.findById(id);
    }

    @PostMapping
    public JobListing save(@RequestBody JobListing jobListing) {
        return jobListingService.save(jobListing);
    }

    @PutMapping("/{id}")
    public JobListing update(@PathVariable Long id, @RequestBody JobListing jobListing) {
        jobListing.setId(id);
        return jobListingService.save(jobListing);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        jobListingService.deleteById(id);
    }

    @GetMapping("/search")
    public List<JobListing> search(@RequestParam String jobTitle, @RequestParam String location, @RequestParam String salaryRange) {
        return jobListingService.search(jobTitle, location, salaryRange);
    }
}
