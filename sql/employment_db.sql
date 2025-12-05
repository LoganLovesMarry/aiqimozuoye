-- 创建数据库
CREATE DATABASE IF NOT EXISTS employment_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE employment_db;

-- 学生表
CREATE TABLE student (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '学生ID',
    name VARCHAR(50) NOT NULL COMMENT '姓名',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    major VARCHAR(100) COMMENT '专业',
    resume_url VARCHAR(500) COMMENT '简历URL',
    status TINYINT DEFAULT 0 COMMENT '就业状态：0-未就业 1-已就业',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_email (email),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='学生表';

-- 企业表
CREATE TABLE company (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '企业ID',
    name VARCHAR(100) NOT NULL COMMENT '企业名称',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '邮箱',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    license VARCHAR(100) COMMENT '营业执照号',
    status TINYINT DEFAULT 0 COMMENT '认证状态：0-未认证 1-已认证',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_email (email),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='企业表';

-- 职位表
CREATE TABLE job (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '职位ID',
    title VARCHAR(100) NOT NULL COMMENT '职位名称',
    description TEXT COMMENT '职位描述',
    salary VARCHAR(50) COMMENT '薪资范围',
    location VARCHAR(100) COMMENT '工作地点',
    company_id BIGINT NOT NULL COMMENT '企业ID',
    status TINYINT DEFAULT 1 COMMENT '职位状态：0-已关闭 1-招聘中',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (company_id) REFERENCES company(id) ON DELETE CASCADE,
    INDEX idx_company_id (company_id),
    INDEX idx_status (status),
    INDEX idx_location (location)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职位表';

-- 投递表
CREATE TABLE application (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '投递ID',
    student_id BIGINT NOT NULL COMMENT '学生ID',
    job_id BIGINT NOT NULL COMMENT '职位ID',
    status TINYINT DEFAULT 0 COMMENT '投递状态：0-待处理 1-已查看 2-面试中 3-已录用 4-已拒绝',
    apply_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '投递时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    FOREIGN KEY (job_id) REFERENCES job(id) ON DELETE CASCADE,
    UNIQUE KEY uk_student_job (student_id, job_id),
    INDEX idx_student_id (student_id),
    INDEX idx_job_id (job_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='投递表';

-- 管理员表
CREATE TABLE admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '管理员ID',
    username VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名',
    password VARCHAR(255) NOT NULL COMMENT '密码',
    role VARCHAR(20) DEFAULT 'ADMIN' COMMENT '角色',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- 公告表
CREATE TABLE announcement (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '公告ID',
    title VARCHAR(200) NOT NULL COMMENT '公告标题',
    content TEXT COMMENT '公告内容',
    admin_id BIGINT COMMENT '发布管理员ID',
    status TINYINT DEFAULT 1 COMMENT '状态：0-隐藏 1-显示',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (admin_id) REFERENCES admin(id) ON DELETE SET NULL,
    INDEX idx_status (status),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- 插入默认管理员账户 (密码: admin123, 需要在应用中使用BCrypt加密)
INSERT INTO admin (username, password, role) VALUES 
('admin', '$2a$10$XN3vGZ3PqV9YXn9LvZkX5OKqPjH.MjZVcJK5pXl6RyKvOYKxQzVqi', 'ADMIN');

-- 插入测试数据
INSERT INTO student (name, email, password, major, status) VALUES 
('张三', 'zhangsan@example.com', '$2a$10$XN3vGZ3PqV9YXn9LvZkX5OKqPjH.MjZVcJK5pXl6RyKvOYKxQzVqi', '计算机科学与技术', 0),
('李四', 'lisi@example.com', '$2a$10$XN3vGZ3PqV9YXn9LvZkX5OKqPjH.MjZVcJK5pXl6RyKvOYKxQzVqi', '软件工程', 0);

INSERT INTO company (name, email, password, license, status) VALUES 
('阿里巴巴', 'hr@alibaba.com', '$2a$10$XN3vGZ3PqV9YXn9LvZkX5OKqPjH.MjZVcJK5pXl6RyKvOYKxQzVqi', '330100000000001', 1),
('腾讯科技', 'hr@tencent.com', '$2a$10$XN3vGZ3PqV9YXn9LvZkX5OKqPjH.MjZVcJK5pXl6RyKvOYKxQzVqi', '440300000000001', 1);

INSERT INTO job (title, description, salary, location, company_id, status) VALUES 
('Java开发工程师', '负责后端开发，要求熟悉Spring Boot框架', '15K-25K', '杭州', 1, 1),
('前端开发工程师', '负责前端开发，要求熟悉Vue.js框架', '12K-20K', '深圳', 2, 1);
