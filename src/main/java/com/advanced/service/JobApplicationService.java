package com.advanced.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.advanced.model.JobApplication;
import com.advanced.repository.JobApplicationRepository;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> findAll() {
        return jobApplicationRepository.findAll();
    }

    public JobApplication findById(Long id) {
        return jobApplicationRepository.findById(id).orElse(null);
    }

    public JobApplication save(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    public void deleteById(Long id) {
        jobApplicationRepository.deleteById(id);
    }
}

