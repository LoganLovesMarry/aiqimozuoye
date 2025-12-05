package com.employment.service;

import com.employment.entity.Student;
import com.employment.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    
    @Autowired
    private StudentMapper studentMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public Student findById(Long id) {
        return studentMapper.findById(id);
    }
    
    public Student findByEmail(String email) {
        return studentMapper.findByEmail(email);
    }
    
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
    
    @Transactional
    public Student register(Student student) {
        // 检查邮箱是否已存在
        if (studentMapper.findByEmail(student.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        // 加密密码
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setStatus(0); // 默认未就业
        studentMapper.insert(student);
        return student;
    }
    
    public Student login(String email, String password) {
        Student student = studentMapper.findByEmail(email);
        if (student == null || !passwordEncoder.matches(password, student.getPassword())) {
            throw new RuntimeException("邮箱或密码错误");
        }
        return student;
    }
    
    @Transactional
    public int update(Student student) {
        return studentMapper.update(student);
    }
    
    @Transactional
    public int updatePassword(Long id, String oldPassword, String newPassword) {
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new RuntimeException("学生不存在");
        }
        if (!passwordEncoder.matches(oldPassword, student.getPassword())) {
            throw new RuntimeException("原密码错误");
        }
        return studentMapper.updatePassword(id, passwordEncoder.encode(newPassword));
    }
    
    @Transactional
    public int delete(Long id) {
        return studentMapper.delete(id);
    }
    
    public double getEmploymentRate() {
        int total = studentMapper.countAll();
        if (total == 0) {
            return 0.0;
        }
        int employed = studentMapper.countEmployed();
        return (double) employed / total * 100;
    }
}
