package com.employment.controller;

import com.employment.common.Result;
import com.employment.entity.Job;
import com.employment.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    
    @Autowired
    private JobService jobService;
    
    @GetMapping("/list")
    public Result<List<Job>> getActiveJobs() {
        List<Job> jobs = jobService.findAllActive();
        return Result.success(jobs);
    }
    
    @GetMapping("/{id}")
    public Result<Job> getById(@PathVariable Long id) {
        Job job = jobService.findById(id);
        if (job != null) {
            return Result.success(job);
        }
        return Result.error("职位不存在");
    }
    
    @GetMapping("/company/{companyId}")
    public Result<List<Job>> getByCompanyId(@PathVariable Long companyId) {
        List<Job> jobs = jobService.findByCompanyId(companyId);
        return Result.success(jobs);
    }
    
    @GetMapping("/search")
    public Result<List<Job>> search(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String location,
                                    @RequestParam(required = false) Integer status) {
        List<Job> jobs = jobService.search(title, location, status);
        return Result.success(jobs);
    }
    
    @PostMapping
    public Result<Job> create(@RequestBody Job job) {
        try {
            Job created = jobService.create(job);
            return Result.success(created);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Job job) {
        try {
            job.setId(id);
            jobService.update(job);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/close")
    public Result<?> close(@PathVariable Long id) {
        try {
            jobService.close(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/open")
    public Result<?> open(@PathVariable Long id) {
        try {
            jobService.open(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            jobService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
