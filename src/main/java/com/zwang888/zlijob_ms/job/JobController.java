package com.zwang888.zlijob_ms.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.addJob(job);
        String responseText = "Job with ID:" + job.getId() + " is added successfully!";
        return new ResponseEntity<>(responseText, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        Boolean isDeleted = jobService.deleteJobById(id);
        if (isDeleted)
            return ResponseEntity.ok("Job with ID:" + id + " is deleted successfully!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job with ID:" + id + " is not found!");
    }

    @PutMapping("/{id}")
    // @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean isUpdated = jobService.updateJobById(id, updatedJob);
        if (isUpdated)
            return ResponseEntity.ok("Job with ID:" + id + " is updated successfully!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job with ID:" + id + " is not found!");
    }
}
