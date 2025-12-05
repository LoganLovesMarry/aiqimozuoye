package com.employment.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Application {
    private Long id;
    private Long studentId;
    private Long jobId;
    private Integer status; // 0-待处理 1-已查看 2-面试中 3-已录用 4-已拒绝
    private Date applyTime;
    private Date updateTime;
    
    // 关联查询字段
    private String studentName;
    private String jobTitle;
    private String companyName;
}
