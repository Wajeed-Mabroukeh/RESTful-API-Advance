package com.advanced.test;

import com.advanced.controller.JobApplicationController;
import com.advanced.controller.JobListingController;
import com.advanced.model.JobApplication;
import com.advanced.model.JobListing;
import com.advanced.service.JobApplicationService;
import com.advanced.service.JobListingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class JobBoardPlatformApplicationTests {

    @Mock
    private JobListingService jobListingService;

    @InjectMocks
    private JobListingController jobListingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllJobListings() {
        // Prepare mock data
        List<JobListing> jobListings = new ArrayList<>();
        jobListings.add(new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $"));
        jobListings.add(new JobListing("Job 2", "Description 2", "Location 2", "Requirements 2", "15000-20000 $"));

        // Mock the service method
        when(jobListingService.findAll()).thenReturn(jobListings);

        // Call the controller method
        List<JobListing> result = jobListingController.findAll();

        // Verify the result
        assertEquals(jobListings.size(), result.size());
        assertEquals(jobListings.get(0).getId(), result.get(0).getId());
        assertEquals(jobListings.get(1).getId(), result.get(1).getId());

        // Verify that the service method was called
        verify(jobListingService, times(1)).findAll();
    }

    @Test
    public void testFindJobListingById() {
        Long id = 1L;

        // Prepare mock data
        JobListing jobListing = new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $");

        // Mock the service method
        when(jobListingService.findById(id)).thenReturn(jobListing);

        // Call the controller method
        JobListing result = jobListingController.findById(id);

        // Verify the result
        assertEquals(jobListing.getId(), result.getId());

        // Verify that the service method was called
        verify(jobListingService, times(1)).findById(id);

        // if not found, throw exception
        id = 2L;

        try {
            jobListingController.findById(id);
        } catch (Exception e) {
            assertEquals("Job listing with ID " + id + " does not exist.", e.getMessage());
        }
    }

    @Test
    public void testCreateJobListing() {
        // Prepare mock data
        JobListing jobListing = new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $");
        JobListing savedJobListing = new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $");
        JobListing jobListing2 = new JobListing("Job 1", "Description 1", "Location 1", null, null);

        // Mock the service method
        when(jobListingService.save(any(JobListing.class))).thenReturn(savedJobListing);

        // Call the controller method
        JobListing result = jobListingController.save(jobListing);

        // Verify the result
        assertEquals(savedJobListing.getId(), result.getId());

        try {
            jobListingController.save(jobListing2);
        } catch (Exception e) {
            assertEquals("All details are required.", e.getMessage());
        }

        // Verify that the service method was called
        verify(jobListingService, times(1)).save(any(JobListing.class));

    }

    @Test
    public void testUpdateJobListing() {
        Long id = 1L;

        // Prepare mock data
        JobListing jobListing = new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $");
        JobListing updatedJobListing = new JobListing("Job 2", "Description 2", "Location 2", "Requirements 2", "15000-20000 $");
        JobListing jobListing2 = new JobListing("Job 1", "Description 1", "Location 1", null, null);

        // Mock the service method
        when(jobListingService.findById(id)).thenReturn(jobListing);
        when(jobListingService.save(any(JobListing.class))).thenReturn(updatedJobListing);

        // Call the controller method
        JobListing result = jobListingController.update(id, updatedJobListing);

        // Verify the result
        assertEquals(updatedJobListing, result);

        try {
            jobListingController.save(jobListing2);
        } catch (Exception e) {
            assertEquals("All details are required.", e.getMessage());
        }

        // Verify that the service method was called
        verify(jobListingService, times(1)).save(any(JobListing.class));

        // if not found, throw exception
        id = 2L;

        try {
            jobListingController.findById(id);
        } catch (Exception e) {
            assertEquals("Job listing with ID " + id + " does not exist.", e.getMessage());
        }
    }

    @Test
    public void testDeleteJobListing() {
        Long id = 1L;

        // Call the controller method
        jobListingController.deleteById(id);

        // Verify that the service method was called
        verify(jobListingService, times(1)).deleteById(id);

        // if not found, throw exception
        id = 2L;
        doThrow(new RuntimeException()).when(jobListingService).deleteById(id);

        try {
            jobListingController.deleteById(id);
        } catch (Exception e) {
            assertEquals("Job listing with ID " + id + " does not exist.", e.getMessage());
        }
    }

    @Test
    public void testSearchJobListing() {
        // Prepare mock data
        List<JobListing> jobListings = new ArrayList<>();
        jobListings.add(new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $"));
        jobListings.add(new JobListing("Job 2", "Description 2", "Location 2", "Requirements 2", "15000-20000 $"));

        // Mock the service method
        when(jobListingService.search("Job 1", "Location 1", "9000-12000 $")).thenReturn(jobListings);
        when(jobListingService.search(null, "Location 1", "9000-12000 $")).thenReturn(jobListings);
        when(jobListingService.search("Job 1", null, "9000-12000 $")).thenReturn(jobListings);
        when(jobListingService.search("Job 1", "Location 1", null)).thenReturn(jobListings);
        when(jobListingService.search(null, null, "9000-12000 $")).thenReturn(jobListings);
        when(jobListingService.search(null, "Location 1", null)).thenReturn(jobListings);
        when(jobListingService.search("Job 1", null, null)).thenReturn(jobListings);
        when(jobListingService.search("Job 2", "Location 2", "15000-20000 $")).thenReturn(jobListings);
        when(jobListingService.search(null, "Location 2", "15000-20000 $")).thenReturn(jobListings);
        when(jobListingService.search("Job 2", null, "15000-20000 $")).thenReturn(jobListings);
        when(jobListingService.search("Job 2", "Location 2", null)).thenReturn(jobListings);
        when(jobListingService.search(null, null, "15000-20000 $")).thenReturn(jobListings);
        when(jobListingService.search(null, "Location 2", null)).thenReturn(jobListings);
        when(jobListingService.search("Job 2", null, null)).thenReturn(jobListings);

        // Call the controller method
        List<JobListing> result = jobListingController.search("Job 1", "Location 1", "9000-12000 $");

        // Verify the result
        assertEquals(jobListings.size(), result.size());
        assertEquals(jobListings.get(0).getId(), result.get(0).getId());
        assertEquals(jobListings.get(1).getId(), result.get(1).getId());

        // Verify that the service method was called
        verify(jobListingService, times(1)).search(anyString(), anyString(), anyString());

        // if not found, throw exception
        try {
            // Call the controller method;
            jobListingController.search("Job 3", "Location 3", "1000-2000 $");

        } catch (Exception e) {
            assertEquals("There are no jobs according to the entered search criteria.", e.getMessage());
        }
    }

    @Test
    public void testExternalAPI() throws NumberFormatException, IOException {
        // Prepare mock data
        List<JobListing> jobListings = new ArrayList<>();
        jobListings.add(new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "8320.797-11094.396 €"));

        // Mock the service method
        when(jobListingService.findAllUSD()).thenReturn(jobListings);

        // Call the controller method
        List<JobListing> result = jobListingController.findAllUSD();

        // Verify the result
        assertEquals(jobListings, result);

        // Verify that the service method was called
        verify(jobListingService, times(1)).findAllUSD();
    }

    @InjectMocks
    private JobApplicationController jobApplicationController;

    @Mock
    private JobApplicationService jobApplicationService;

    @Test
    public void testFindAllJobApplications() {
        // Prepare mock data
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplications.add(
                new JobApplication(1L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter"));

        // Mock the service method
        when(jobApplicationService.findAll()).thenReturn(jobApplications);

        // Call the controller method
        List<JobApplication> result = jobApplicationController.findAll();

        // Verify the result
        assertEquals(jobApplications, result);

        // Verify that the service method was called
        verify(jobApplicationService, times(1)).findAll();
    }

    @Test
    public void testFindJobApplicationById() {
        Long id = 1L;

        // Prepare mock data
        JobApplication jobApplication = new JobApplication(1L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter");

        // Mock the service method
        when(jobApplicationService.findById(id)).thenReturn(jobApplication);

        // Call the controller method
        JobApplication result = jobApplicationController.findById(id);

        // Verify the result
        assertEquals(jobApplication, result);

        // Verify that the service method was called
        verify(jobApplicationService, times(1)).findById(id);

        // if not found, throw exception
        id = 2L;

        try {
            jobApplicationController.findById(id);
        } catch (Exception e) {
            assertEquals("Job application with ID " + id + " does not exist.", e.getMessage());
        }

    }

    @Test
    public void testCreateJobApplication() {
        // Prepare mock data
        JobApplication jobApplication = new JobApplication(1L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter");
        JobListing jobListing = new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $");
        JobApplication jobApplication2 = new JobApplication(1L, null, null, null, null);
        JobApplication jobApplication3 = new JobApplication(3L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter");

        // Mock the service methods
        when(jobApplicationService.save(any(JobApplication.class))).thenReturn(jobApplication);
        when(jobListingService.findById(1L)).thenReturn(jobListing);

        // Call the controller method
        JobApplication result = jobApplicationController.save(jobApplication);

        // Verify the result
        assertEquals(jobApplication, result);

        // Verify that the service methods were called
        verify(jobApplicationService, times(1)).save(any(JobApplication.class));
        verify(jobListingService, times(1)).findById(1L);

        // if job listing not found, throw exception
        try {
            jobListingController.findById(jobApplication3.getJobId());
        } catch (Exception e) {
            assertEquals("Job listing with ID " + jobApplication3.getJobId() + " does not exist.", e.getMessage());
        }

        // if job application details are required, throw exception
        try {
            jobApplicationController.save(jobApplication2);
        } catch (Exception e) {
            assertEquals("All details are required.", e.getMessage());
        }
    }

    @Test
    public void testUpdateJobApplication() {
        Long id = 1L;
        // Prepare mock data
        JobApplication jobApplication = new JobApplication(1L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter");
        JobApplication updatedJobApplication = new JobApplication(1L, "Mahmoud Hamo", "Mahmoudhamo15@outlook.com", "MahmoudNewResume.pdf", "Updated Cover letter");
        JobListing jobListing = new JobListing("Job 1", "Description 1", "Location 1", "Requirements 1", "9000-12000 $");
        JobApplication updatedJobApplication2 = new JobApplication(1L, null, null, null, null);
        JobApplication updatedJobApplication3 = new JobApplication(3L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter");

        // Mock the service methods
        when(jobApplicationService.findById(id)).thenReturn(jobApplication);
        when(jobApplicationService.save(any(JobApplication.class))).thenReturn(updatedJobApplication);
        when(jobListingService.findById(updatedJobApplication.getJobId())).thenReturn(jobListing);

        // Call the controller method
        JobApplication result = jobApplicationController.update(id, updatedJobApplication);

        // Verify the result
        assertEquals(updatedJobApplication, result);

        // Verify that the service methods were called
        verify(jobApplicationService, times(1)).findById(id);
        verify(jobApplicationService, times(1)).save(any(JobApplication.class));
        verify(jobListingService, times(1)).findById(updatedJobApplication.getJobId());

        // if job listing not found, throw exception

        try {
            jobListingController.findById(updatedJobApplication3.getJobId());
        } catch (Exception e) {
            assertEquals("Job listing with ID " + updatedJobApplication3.getJobId() + " does not exist.", e.getMessage());
        }

        // if job application not found, throw exception
        id = 2L;

        try {
            jobApplicationController.findById(id);
        } catch (Exception e) {
            assertEquals("Job application with ID " + id + " does not exist.", e.getMessage());
        }

        // if job application details are required, throw exception
        try {
            jobApplicationController.update(1L, updatedJobApplication2);
        } catch (Exception e) {
            assertEquals("All details are required.", e.getMessage());
        }
    }

    @Test
    public void testDeleteJobApplication() {
        Long id = 1L;

        // Prepare mock data
        JobApplication jobApplication = new JobApplication(1L, "Mahmoud Hamo", "Mahmoudhamo15@gmail.com", "MahmoudResume.pdf", "Cover letter");

        // Mock the service method
        when(jobApplicationService.findById(id)).thenReturn(jobApplication);

        // Call the controller method
        jobApplicationController.deleteById(id);

        // Verify that the service method was called
        verify(jobApplicationService, times(1)).deleteById(id);

        // if job application not found, throw exception
        id = 2L;

        try {
            jobApplicationController.findById(id);
        } catch (Exception e) {
            assertEquals("Job application with ID " + id + " does not exist.", e.getMessage());
        }
    }

}