package com.employment.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Job {
    private Long id;
    private String title;
    private String description;
    private String salary;
    private String location;
    private Long companyId;
    private Integer status; // 0-已关闭 1-招聘中
    private Date createTime;
    private Date updateTime;
    
    // 关联查询字段
    private String companyName;
}
