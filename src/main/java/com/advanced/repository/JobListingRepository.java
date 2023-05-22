package com.advanced.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.advanced.model.JobListing;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    List<JobListing> findByJobTitleContainingIgnoreCase(String jobTitle);
    List<JobListing> findBySalaryRangeContainingIgnoreCase(String salaryRange);
    List<JobListing> findByLocationContainingIgnoreCase(String location);
    List<JobListing> findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCaseAndSalaryRangeContainingIgnoreCase(
            String jobTitle, String location, String salaryRange);
    List<JobListing> findByJobTitleContainingIgnoreCaseAndLocationContainingIgnoreCase(String jobTitle,
            String location);
    List<JobListing> findByJobTitleContainingIgnoreCaseAndSalaryRangeContainingIgnoreCase(String jobTitle,
            String salaryRange);
    List<JobListing> findByLocationContainingIgnoreCaseAndSalaryRangeContainingIgnoreCase(String location,
            String salaryRange);
}