package com.employment.controller;

import com.employment.common.Result;
import com.employment.entity.Application;
import com.employment.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    
    @Autowired
    private ApplicationService applicationService;
    
    @PostMapping("/apply")
    public Result<Application> apply(@RequestBody Map<String, Long> params) {
        try {
            Long studentId = params.get("studentId");
            Long jobId = params.get("jobId");
            Application application = applicationService.apply(studentId, jobId);
            return Result.success(application);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public Result<Application> getById(@PathVariable Long id) {
        Application application = applicationService.findById(id);
        if (application != null) {
            return Result.success(application);
        }
        return Result.error("投递记录不存在");
    }
    
    @GetMapping("/student/{studentId}")
    public Result<List<Application>> getByStudentId(@PathVariable Long studentId) {
        List<Application> applications = applicationService.findByStudentId(studentId);
        return Result.success(applications);
    }
    
    @GetMapping("/company/{companyId}")
    public Result<List<Application>> getByCompanyId(@PathVariable Long companyId) {
        List<Application> applications = applicationService.findByCompanyId(companyId);
        return Result.success(applications);
    }
    
    @PutMapping("/{id}/status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> params) {
        try {
            Integer status = params.get("status");
            applicationService.updateStatus(id, status);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/view")
    public Result<?> view(@PathVariable Long id) {
        try {
            applicationService.view(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/interview")
    public Result<?> interview(@PathVariable Long id) {
        try {
            applicationService.interview(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/hire")
    public Result<?> hire(@PathVariable Long id) {
        try {
            applicationService.hire(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/reject")
    public Result<?> reject(@PathVariable Long id) {
        try {
            applicationService.reject(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            applicationService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
