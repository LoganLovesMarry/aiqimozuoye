package com.employment.service;

import com.employment.entity.Job;
import com.employment.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class JobService {
    
    @Autowired
    private JobMapper jobMapper;
    
    public Job findById(Long id) {
        return jobMapper.findById(id);
    }
    
    public List<Job> findAllActive() {
        return jobMapper.findAllActive();
    }
    
    public List<Job> findByCompanyId(Long companyId) {
        return jobMapper.findByCompanyId(companyId);
    }
    
    public List<Job> search(String title, String location, Integer status) {
        return jobMapper.search(title, location, status);
    }
    
    @Transactional
    public Job create(Job job) {
        job.setStatus(1); // 默认招聘中
        jobMapper.insert(job);
        return job;
    }
    
    @Transactional
    public int update(Job job) {
        return jobMapper.update(job);
    }
    
    @Transactional
    public int close(Long id) {
        return jobMapper.updateStatus(id, 0);
    }
    
    @Transactional
    public int open(Long id) {
        return jobMapper.updateStatus(id, 1);
    }
    
    @Transactional
    public int delete(Long id) {
        return jobMapper.delete(id);
    }
}
