package com.employment.service;

import com.employment.entity.Admin;
import com.employment.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminService {
    
    @Autowired
    private AdminMapper adminMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Admin findById(Long id) {
        return adminMapper.findById(id);
    }
    
    public Admin findByUsername(String username) {
        return adminMapper.findByUsername(username);
    }
    
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }
    
    public Admin login(String username, String password) {
        Admin admin = adminMapper.findByUsername(username);
        if (admin == null || !passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        return admin;
    }
    
    @Transactional
    public Admin create(Admin admin) {
        if (adminMapper.findByUsername(admin.getUsername()) != null) {
            throw new RuntimeException("用户名已存在");
        }
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
        return admin;
    }
    
    @Transactional
    public int updatePassword(Long id, String oldPassword, String newPassword) {
        Admin admin = adminMapper.findById(id);
        if (admin == null) {
            throw new RuntimeException("管理员不存在");
        }
        if (!passwordEncoder.matches(oldPassword, admin.getPassword())) {
            throw new RuntimeException("原密码错误");
        }
        return adminMapper.updatePassword(id, passwordEncoder.encode(newPassword));
    }
    
    @Transactional
    public int delete(Long id) {
        return adminMapper.delete(id);
    }
}
