package com.zwang888.zlijob_ms.job.impl;

import com.zwang888.zlijob_ms.job.Job;
import com.zwang888.zlijob_ms.job.JobRepository;
import com.zwang888.zlijob_ms.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void addJob(@RequestBody Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean deleteJobById(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
            return true;
        }

        return false;
        //        Iterator<Job> iterator = jobs.iterator();
        //        while (iterator.hasNext()) {
        //            if (iterator.next().getId().equals(id)) {
        //                iterator.remove();
        //                return true;
        //            }
        //        }
        //        return false;
    }

    @Override
    public boolean updateJobById(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
