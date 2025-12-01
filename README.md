# 毕业生就业信息管理系统

## 项目简介
这是一个基于Spring Boot + MyBatis的毕业生就业信息管理系统，实现了学生求职、企业招聘、学校管理三位一体的功能。

## 技术栈
- **后端框架**: Spring Boot 2.7.14
- **持久层框架**: MyBatis 2.2.2
- **数据库**: MySQL 8.0
- **安全框架**: Spring Security
- **连接池**: Druid
- **构建工具**: Maven
- **开发语言**: Java 8

## 项目结构
```
aiqimozuoye-1/
├── backend/                    # 后端项目
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/employment/
│   │   │   │       ├── EmploymentApplication.java  # 启动类
│   │   │   │       ├── common/                     # 通用类
│   │   │   │       │   └── Result.java             # 统一返回结果
│   │   │   │       ├── config/                     # 配置类
│   │   │   │       │   ├── CorsConfig.java         # 跨域配置
│   │   │   │       │   └── SecurityConfig.java     # 安全配置
│   │   │   │       ├── controller/                 # 控制器层
│   │   │   │       │   ├── StudentController.java
│   │   │   │       │   ├── CompanyController.java
│   │   │   │       │   ├── JobController.java
│   │   │   │       │   ├── ApplicationController.java
│   │   │   │       │   ├── AdminController.java
│   │   │   │       │   └── AnnouncementController.java
│   │   │   │       ├── entity/                     # 实体类
│   │   │   │       │   ├── Student.java
│   │   │   │       │   ├── Company.java
│   │   │   │       │   ├── Job.java
│   │   │   │       │   ├── Application.java
│   │   │   │       │   ├── Admin.java
│   │   │   │       │   └── Announcement.java
│   │   │   │       ├── mapper/                     # 数据访问层
│   │   │   │       │   ├── StudentMapper.java
│   │   │   │       │   ├── CompanyMapper.java
│   │   │   │       │   ├── JobMapper.java
│   │   │   │       │   ├── ApplicationMapper.java
│   │   │   │       │   ├── AdminMapper.java
│   │   │   │       │   └── AnnouncementMapper.java
│   │   │   │       └── service/                    # 业务逻辑层
│   │   │   │           ├── StudentService.java
│   │   │   │           ├── CompanyService.java
│   │   │   │           ├── JobService.java
│   │   │   │           ├── ApplicationService.java
│   │   │   │           ├── AdminService.java
│   │   │   │           └── AnnouncementService.java
│   │   │   └── resources/
│   │   │       └── application.yml                 # 配置文件
│   └── pom.xml                                     # Maven配置
├── docs/                       # 文档
├── sql/                        # SQL脚本
│   └── employment_db.sql       # 数据库初始化脚本
└── README.md                   # 项目说明
```

## 功能模块

### 1. 学生模块
- 注册/登录
- 个人信息管理
- 简历上传与管理
- 职位搜索
- 职位投递
- 投递记录查看
- 就业状态跟踪

### 2. 企业模块
- 注册/登录
- 企业信息管理
- 职位发布
- 职位管理（开启/关闭/编辑/删除）
- 简历筛选
- 投递记录查看
- 候选人状态管理（查看/面试/录用/拒绝）

### 3. 管理员模块
- 管理员登录
- 用户审核（企业认证）
- 学生管理
- 企业管理
- 就业数据统计
- 公告发布与管理

## API接口文档

### 学生接口
- `POST /api/student/register` - 学生注册
- `POST /api/student/login` - 学生登录
- `GET /api/student/{id}` - 获取学生信息
- `GET /api/student/list` - 获取所有学生
- `PUT /api/student/{id}` - 更新学生信息
- `POST /api/student/uploadResume` - 上传简历
- `PUT /api/student/updatePassword` - 修改密码
- `DELETE /api/student/{id}` - 删除学生

### 企业接口
- `POST /api/company/register` - 企业注册
- `POST /api/company/login` - 企业登录
- `GET /api/company/{id}` - 获取企业信息
- `GET /api/company/list` - 获取所有企业
- `GET /api/company/pending` - 获取待审核企业
- `PUT /api/company/{id}` - 更新企业信息
- `PUT /api/company/{id}/approve` - 审核通过
- `PUT /api/company/{id}/reject` - 审核拒绝
- `DELETE /api/company/{id}` - 删除企业

### 职位接口
- `GET /api/job/list` - 获取所有活跃职位
- `GET /api/job/{id}` - 获取职位详情
- `GET /api/job/company/{companyId}` - 获取企业的职位列表
- `GET /api/job/search` - 搜索职位
- `POST /api/job` - 发布职位
- `PUT /api/job/{id}` - 更新职位
- `PUT /api/job/{id}/close` - 关闭职位
- `PUT /api/job/{id}/open` - 开启职位
- `DELETE /api/job/{id}` - 删除职位

### 投递接口
- `POST /api/application/apply` - 投递职位
- `GET /api/application/{id}` - 获取投递详情
- `GET /api/application/student/{studentId}` - 获取学生的投递记录
- `GET /api/application/company/{companyId}` - 获取企业收到的投递
- `PUT /api/application/{id}/status` - 更新投递状态
- `PUT /api/application/{id}/view` - 标记为已查看
- `PUT /api/application/{id}/interview` - 通知面试
- `PUT /api/application/{id}/hire` - 录用
- `PUT /api/application/{id}/reject` - 拒绝
- `DELETE /api/application/{id}` - 删除投递记录

### 管理员接口
- `POST /api/admin/login` - 管理员登录
- `GET /api/admin/list` - 获取所有管理员
- `POST /api/admin` - 创建管理员
- `PUT /api/admin/updatePassword` - 修改密码
- `GET /api/admin/statistics/employmentRate` - 获取就业率统计
- `DELETE /api/admin/{id}` - 删除管理员

### 公告接口
- `GET /api/announcement/list` - 获取所有公告
- `GET /api/announcement/active` - 获取显示的公告
- `GET /api/announcement/{id}` - 获取公告详情
- `POST /api/announcement` - 发布公告
- `PUT /api/announcement/{id}` - 更新公告
- `PUT /api/announcement/{id}/hide` - 隐藏公告
- `PUT /api/announcement/{id}/show` - 显示公告
- `DELETE /api/announcement/{id}` - 删除公告

## 安装与运行

### 1. 环境要求
- JDK 8+
- Maven 3.6+
- MySQL 8.0+

### 2. 数据库配置
1. 创建数据库并执行SQL脚本：
```bash
mysql -u root -p < sql/employment_db.sql
```

2. 修改配置文件 `backend/src/main/resources/application.yml`：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/employment_db
    username: root      # 修改为你的数据库用户名
    password: root      # 修改为你的数据库密码
```

### 3. 启动项目
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

或者使用IDE（IntelliJ IDEA / Eclipse）：
1. 导入Maven项目
2. 等待依赖下载完成
3. 运行 `EmploymentApplication.java`

### 4. 访问系统
- 后端API地址：http://localhost:8080/api
- API文档：参考上面的接口列表

### 5. 测试账号
默认已插入的测试数据（密码均为：admin123）：
- 管理员：username: `admin`
- 学生：email: `zhangsan@example.com` / `lisi@example.com`
- 企业：email: `hr@alibaba.com` / `hr@tencent.com`

## 数据库表结构

### student - 学生表
- id: 学生ID
- name: 姓名
- email: 邮箱（登录账号）
- password: 密码（BCrypt加密）
- major: 专业
- resume_url: 简历URL
- status: 就业状态（0-未就业，1-已就业）
- create_time: 创建时间
- update_time: 更新时间

### company - 企业表
- id: 企业ID
- name: 企业名称
- email: 邮箱（登录账号）
- password: 密码（BCrypt加密）
- license: 营业执照号
- status: 认证状态（0-未认证，1-已认证）
- create_time: 创建时间
- update_time: 更新时间

### job - 职位表
- id: 职位ID
- title: 职位名称
- description: 职位描述
- salary: 薪资范围
- location: 工作地点
- company_id: 企业ID（外键）
- status: 职位状态（0-已关闭，1-招聘中）
- create_time: 创建时间
- update_time: 更新时间

### application - 投递表
- id: 投递ID
- student_id: 学生ID（外键）
- job_id: 职位ID（外键）
- status: 投递状态（0-待处理，1-已查看，2-面试中，3-已录用，4-已拒绝）
- apply_time: 投递时间
- update_time: 更新时间

### admin - 管理员表
- id: 管理员ID
- username: 用户名（登录账号）
- password: 密码（BCrypt加密）
- role: 角色
- create_time: 创建时间
- update_time: 更新时间

### announcement - 公告表
- id: 公告ID
- title: 公告标题
- content: 公告内容
- admin_id: 发布管理员ID（外键）
- status: 状态（0-隐藏，1-显示）
- create_time: 创建时间
- update_time: 更新时间

## 注意事项
1. 首次运行前请确保MySQL服务已启动
2. 确保数据库字符集为UTF-8
3. 文件上传目录默认为项目根目录的 `uploads/resumes/`
4. 本系统使用BCrypt加密密码，默认测试密码均为 `admin123`
5. JWT功能已简化实现，生产环境需要完善token验证机制

## 后续扩展
- [ ] 完善JWT认证机制
- [ ] 添加邮件通知功能
- [ ] 实现简历解析
- [ ] 增加数据可视化看板
- [ ] 开发移动端应用
- [ ] 集成AI智能推荐

## 开发团队
- 开发时间：2024年
- 技术支持：基于Spring Boot生态

## 许可证
MIT License
