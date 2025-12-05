package com.employment.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Announcement {
    private Long id;
    private String title;
    private String content;
    private Long adminId;
    private Integer status; // 0-隐藏 1-显示
    private Date createTime;
    private Date updateTime;
}
