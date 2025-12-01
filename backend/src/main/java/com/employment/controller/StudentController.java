package com.employment.controller;

import com.employment.common.Result;
import com.employment.entity.Student;
import com.employment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @PostMapping("/register")
    public Result<Student> register(@RequestBody Student student) {
        try {
            Student registered = studentService.register(student);
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
            Student student = studentService.login(email, password);
            // 清除密码字段
            student.setPassword(null);
            Map<String, Object> data = new HashMap<>();
            data.put("user", student);
            data.put("token", "mock-token-" + student.getId()); // 简化JWT实现
            return Result.success(data);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public Result<Student> getById(@PathVariable Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            student.setPassword(null);
            return Result.success(student);
        }
        return Result.error("学生不存在");
    }
    
    @GetMapping("/list")
    public Result<List<Student>> getAll() {
        List<Student> students = studentService.findAll();
        students.forEach(s -> s.setPassword(null));
        return Result.success(students);
    }
    
    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Student student) {
        try {
            student.setId(id);
            studentService.update(student);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @PostMapping("/uploadResume")
    public Result<Map<String, String>> uploadResume(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        
        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String filename = UUID.randomUUID().toString() + extension;
            
            // 创建上传目录
            String uploadDir = "uploads/resumes/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 保存文件
            File dest = new File(uploadDir + filename);
            file.transferTo(dest);
            
            Map<String, String> data = new HashMap<>();
            data.put("url", "/" + uploadDir + filename);
            return Result.success(data);
        } catch (IOException e) {
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }
    
    @PutMapping("/updatePassword")
    public Result<?> updatePassword(@RequestBody Map<String, String> params) {
        try {
            Long id = Long.parseLong(params.get("id"));
            String oldPassword = params.get("oldPassword");
            String newPassword = params.get("newPassword");
            studentService.updatePassword(id, oldPassword, newPassword);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        try {
            studentService.delete(id);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
