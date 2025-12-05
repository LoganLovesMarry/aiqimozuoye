package com.employment.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Company {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String license;
    private Integer status; // 0-未认证 1-已认证
    private Date createTime;
    private Date updateTime;
}
