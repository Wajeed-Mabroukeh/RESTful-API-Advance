package com.advanced.controller;

import com.advanced.model.JobApplication;
import com.advanced.service.JobApplicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job-applications")
public class JobApplicationController {
    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping
    public List<JobApplication> findAll() {
        return jobApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public JobApplication findById(@PathVariable Long id) {
        return jobApplicationService.findById(id);
    }

    @PostMapping
    public JobApplication save(@RequestBody JobApplication jobApplication) {
        return jobApplicationService.save(jobApplication);
    }

    @PutMapping("/{id}")
    public JobApplication update(@PathVariable Long id, @RequestBody JobApplication jobApplication) {
        jobApplication.setId(id);
        return jobApplicationService.save(jobApplication);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        jobApplicationService.deleteById(id);
    }
}
