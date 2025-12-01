package com.employment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.employment.mapper")
public class EmploymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmploymentApplication.class, args);
    }
}
