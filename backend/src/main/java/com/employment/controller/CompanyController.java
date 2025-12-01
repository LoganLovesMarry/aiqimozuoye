package com.employment.controller;

import com.employment.common.Result;
import com.employment.entity.Company;
import com.employment.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;
    
    @PostMapping("/register")
    public Result<Company> register(@RequestBody Company company) {
        try {
            Company registered = companyService.register(company);
            return Result.success(registered);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        try {
            String email = params.get("email");
            String password = params.get("password");
            Company company = companyService.login(email, password);
            company.setPassword(null);
            Map<String, Object> data = new HashMap<>();
            data.put("user", company);
            data.put("token", "mock-token-company-" + company.getId());
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public Result<Company> getById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        if (company != null) {
            company.setPassword(null);
            return Result.success(company);
        }
        return Result.error("企业不存在");
    }
    
    @GetMapping("/list")
    public Result<List<Company>> getAll() {
        List<Company> companies = companyService.findAll();
        companies.forEach(c -> c.setPassword(null));
        return Result.success(companies);
    }
    
    @GetMapping("/pending")
    public Result<List<Company>> getPending() {
        List<Company> companies = companyService.findByStatus(0);
        companies.forEach(c -> c.setPassword(null));
        return Result.success(companies);
    }
    
    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Company company) {
        try {
            company.setId(id);
            companyService.update(company);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/approve")
    public Result<?> approve(@PathVariable Long id) {
        try {
            companyService.approve(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/{id}/reject")
    public Result<?> reject(@PathVariable Long id) {
        try {
            companyService.reject(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            companyService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
