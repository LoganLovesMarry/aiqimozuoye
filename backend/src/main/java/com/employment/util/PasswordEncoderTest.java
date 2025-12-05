package com.employment.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密工具类
 * 用于生成BCrypt加密的密码
 */
public class PasswordEncoderTest {
    
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        // 生成常用密码的BCrypt哈希值
        System.out.println("=== 管理员密码生成工具 ===\n");
        
        String[] passwords = {"admin123", "123456", "password", "admin"};
        
        for (String password : passwords) {
            String encoded = passwordEncoder.encode(password);
            System.out.println("原始密码: " + password);
            System.out.println("加密后: " + encoded);
            System.out.println("验证: " + passwordEncoder.matches(password, encoded));
            System.out.println("-----------------------------------");
        }
        
        // 生成SQL插入语句
        System.out.println("\n=== SQL 插入语句 ===\n");
        System.out.println("-- 管理员账号: admin, 密码: admin123");
        System.out.println("INSERT INTO admin (username, password, role) VALUES ('admin', '" + 
            passwordEncoder.encode("admin123") + "', 'ADMIN');\n");
        
        System.out.println("-- 管理员账号: lyl, 密码: 123456");
        System.out.println("INSERT INTO admin (username, password, role) VALUES ('lyl', '" + 
            passwordEncoder.encode("123456") + "', 'ADMIN');\n");
    }
}
