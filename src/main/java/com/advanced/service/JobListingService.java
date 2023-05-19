package com.advanced.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advanced.repository.JobListingRepository;
import com.advanced.model.JobListing;

@Service
public class JobListingService {
    @Autowired
    private JobListingRepository jobListingRepository;

    public List<JobListing> findAll() {
        return jobListingRepository.findAll();
    }

    public JobListing findById(Long id) {
        return jobListingRepository.findById(id).orElse(null);
    }

    public JobListing save(JobListing jobListing) {
        return jobListingRepository.save(jobListing);
    }

    public void deleteById(Long id) {
        jobListingRepository.deleteById(id);
    }

    public List<JobListing> searchByJobTitle(String jobTitle) {
        return jobListingRepository.findByJobTitleContainingIgnoreCase(jobTitle);
    }

    public List<JobListing> searchByLocation(String location) {
        return jobListingRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<JobListing> search(String jobTitle, String location, String salaryRange) {
        if (jobTitle != null && location != null && salaryRange != null) {
            return jobListingRepository.findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryRangeContainingIgnoreCase(jobTitle, location, salaryRange);
        } else if (jobTitle != null && location != null) {
            return jobListingRepository.findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(jobTitle, location);
        } else if (jobTitle != null && salaryRange != null) {
            return jobListingRepository.findByJobTitleContainingIgnoreCaseAndSalaryRangeContainingIgnoreCase(jobTitle, salaryRange);
        } else if (location != null && salaryRange != null) {
            return jobListingRepository.findByLocationContainingIgnoreCaseAndSalaryRangeContainingIgnoreCase(location, salaryRange);
        } else if (jobTitle != null) {
            return jobListingRepository.findByJobTitleContainingIgnoreCase(jobTitle);
        } else if (location != null) {
            return jobListingRepository.findByLocationContainingIgnoreCase(location);
        } else if (salaryRange != null) {
            return jobListingRepository.findBySalaryRangeContainingIgnoreCase(salaryRange);
        } else {
            return jobListingRepository.findAll();
        }
    }

}
