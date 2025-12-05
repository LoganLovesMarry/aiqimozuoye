package com.employment.service;

import com.employment.entity.Company;
import com.employment.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyMapper companyMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Company findById(Long id) {
        return companyMapper.findById(id);
    }
    
    public Company findByEmail(String email) {
        return companyMapper.findByEmail(email);
    }
    
    public List<Company> findAll() {
        return companyMapper.findAll();
    }
    
    public List<Company> findByStatus(Integer status) {
        return companyMapper.findByStatus(status);
    }
    
    @Transactional
    public Company register(Company company) {
        // 检查邮箱是否已存在
        if (companyMapper.findByEmail(company.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        // 加密密码
        company.setPassword(passwordEncoder.encode(company.getPassword()));
        company.setStatus(0); // 默认未认证
        companyMapper.insert(company);
        return company;
    }
    
    public Company login(String email, String password) {
        Company company = companyMapper.findByEmail(email);
        if (company == null || !passwordEncoder.matches(password, company.getPassword())) {
            throw new RuntimeException("邮箱或密码错误");
        }
        return company;
    }
    
    @Transactional
    public int update(Company company) {
        return companyMapper.update(company);
    }
    
    @Transactional
    public int approve(Long id) {
        return companyMapper.updateStatus(id, 1);
    }
    
    @Transactional
    public int reject(Long id) {
        return companyMapper.updateStatus(id, 0);
    }
    
    @Transactional
    public int delete(Long id) {
        return companyMapper.delete(id);
    }
}
