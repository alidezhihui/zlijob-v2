package com.zwang888.zlijob_ms.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void addJob(Job job);

    Job getJobById(Long id);

    Boolean deleteJobById(Long id);

    boolean updateJobById(Long id, Job updatedJob);
}
