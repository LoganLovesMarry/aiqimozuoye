package com.employment.controller;

import com.employment.common.Result;
import com.employment.entity.Admin;
import com.employment.service.AdminService;
import com.employment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private StudentService studentService;
    
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        try {
            String username = params.get("username");
            String password = params.get("password");
            Admin admin = adminService.login(username, password);
            admin.setPassword(null);
            Map<String, Object> data = new HashMap<>();
            data.put("user", admin);
            data.put("token", "mock-token-admin-" + admin.getId());
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/list")
    public Result<List<Admin>> getAll() {
        List<Admin> admins = adminService.findAll();
        admins.forEach(a -> a.setPassword(null));
        return Result.success(admins);
    }
    
    @PostMapping("/register")
    public Result<Admin> register(@RequestBody Admin admin) {
        try {
            Admin created = adminService.create(admin);
            created.setPassword(null);
            return Result.success(created);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PostMapping
    public Result<Admin> create(@RequestBody Admin admin) {
        try {
            Admin created = adminService.create(admin);
            created.setPassword(null);
            return Result.success(created);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PutMapping("/updatePassword")
    public Result<?> updatePassword(@RequestBody Map<String, String> params) {
        try {
            Long id = Long.parseLong(params.get("id"));
            String oldPassword = params.get("oldPassword");
            String newPassword = params.get("newPassword");
            adminService.updatePassword(id, oldPassword, newPassword);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/statistics/employmentRate")
    public Result<Map<String, Object>> getEmploymentRate() {
        try {
            double rate = studentService.getEmploymentRate();
            Map<String, Object> data = new HashMap<>();
            data.put("rate", rate);
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            adminService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
