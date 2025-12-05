package com.employment.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Student {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String major;
    private String resumeUrl;
    private Integer status; // 0-未就业 1-已就业
    private Date createTime;
    private Date updateTime;
}
