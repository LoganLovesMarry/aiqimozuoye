package com.employment.service;

import com.employment.entity.Application;
import com.employment.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApplicationService {
    
    @Autowired
    private ApplicationMapper applicationMapper;
    
    public Application findById(Long id) {
        return applicationMapper.findById(id);
    }
    
    public List<Application> findByStudentId(Long studentId) {
        return applicationMapper.findByStudentId(studentId);
    }
    
    public List<Application> findByCompanyId(Long companyId) {
        return applicationMapper.findByCompanyId(companyId);
    }
    
    @Transactional
    public Application apply(Long studentId, Long jobId) {
        // 检查是否已投递
        Application existing = applicationMapper.findByStudentAndJob(studentId, jobId);
        if (existing != null) {
            throw new RuntimeException("已经投递过该职位");
        }
        
        Application application = new Application();
        application.setStudentId(studentId);
        application.setJobId(jobId);
        application.setStatus(0); // 待处理
        applicationMapper.insert(application);
        return application;
    }
    
    @Transactional
    public int updateStatus(Long id, Integer status) {
        return applicationMapper.updateStatus(id, status);
    }
    
    @Transactional
    public int view(Long id) {
        return applicationMapper.updateStatus(id, 1);
    }
    
    @Transactional
    public int interview(Long id) {
        return applicationMapper.updateStatus(id, 2);
    }
    
    @Transactional
    public int hire(Long id) {
        return applicationMapper.updateStatus(id, 3);
    }
    
    @Transactional
    public int reject(Long id) {
        return applicationMapper.updateStatus(id, 4);
    }
    
    @Transactional
    public int delete(Long id) {
        return applicationMapper.delete(id);
    }
}
