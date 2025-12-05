# API测试示例

本文档提供了完整的API测试示例，可以使用Postman、cURL或其他HTTP客户端进行测试。

## 基础信息
- 基础URL: `http://localhost:8080/api`
- Content-Type: `application/json`

---

## 1. 学生模块

### 1.1 学生注册
```http
POST /api/student/register
Content-Type: application/json

{
  "name": "王五",
  "email": "wangwu@example.com",
  "password": "123456",
  "major": "软件工程"
}
```

### 1.2 学生登录
```http
POST /api/student/login
Content-Type: application/json

{
  "email": "zhangsan@example.com",
  "password": "admin123"
}
```

**返回示例:**
```json
{
  "code": 200,
  "message": "成功",
  "data": {
    "user": {
      "id": 1,
      "name": "张三",
      "email": "zhangsan@example.com",
      "major": "计算机科学与技术",
      "status": 0
    },
    "token": "mock-token-1"
  }
}
```

### 1.3 获取学生信息
```http
GET /api/student/1
```

### 1.4 更新学生信息
```http
PUT /api/student/1
Content-Type: application/json

{
  "name": "张三",
  "major": "计算机科学与技术",
  "status": 1
}
```

### 1.5 上传简历
```http
POST /api/student/uploadResume
Content-Type: multipart/form-data

file: [选择PDF或Word文件]
```

### 1.6 修改密码
```http
PUT /api/student/updatePassword
Content-Type: application/json

{
  "id": "1",
  "oldPassword": "admin123",
  "newPassword": "newpassword123"
}
```

---

## 2. 企业模块

### 2.1 企业注册
```http
POST /api/company/register
Content-Type: application/json

{
  "name": "字节跳动",
  "email": "hr@bytedance.com",
  "password": "123456",
  "license": "110000000000001"
}
```

### 2.2 企业登录
```http
POST /api/company/login
Content-Type: application/json

{
  "email": "hr@alibaba.com",
  "password": "admin123"
}
```

### 2.3 获取所有企业
```http
GET /api/company/list
```

### 2.4 获取待审核企业
```http
GET /api/company/pending
```

### 2.5 审核通过企业
```http
PUT /api/company/1/approve
```

### 2.6 审核拒绝企业
```http
PUT /api/company/1/reject
```

---

## 3. 职位模块

### 3.1 获取所有活跃职位
```http
GET /api/job/list
```

**返回示例:**
```json
{
  "code": 200,
  "message": "成功",
  "data": [
    {
      "id": 1,
      "title": "Java开发工程师",
      "description": "负责后端开发，要求熟悉Spring Boot框架",
      "salary": "15K-25K",
      "location": "杭州",
      "companyId": 1,
      "companyName": "阿里巴巴",
      "status": 1
    }
  ]
}
```

### 3.2 获取职位详情
```http
GET /api/job/1
```

### 3.3 搜索职位
```http
GET /api/job/search?title=Java&location=杭州&status=1
```

### 3.4 发布职位
```http
POST /api/job
Content-Type: application/json

{
  "title": "前端开发工程师",
  "description": "负责前端开发，熟悉React、Vue框架",
  "salary": "12K-20K",
  "location": "北京",
  "companyId": 1
}
```

### 3.5 更新职位
```http
PUT /api/job/1
Content-Type: application/json

{
  "title": "高级Java开发工程师",
  "description": "负责核心业务开发",
  "salary": "20K-35K",
  "location": "杭州",
  "status": 1
}
```

### 3.6 关闭职位
```http
PUT /api/job/1/close
```

### 3.7 开启职位
```http
PUT /api/job/1/open
```

### 3.8 删除职位
```http
DELETE /api/job/1
```

---

## 4. 投递模块

### 4.1 投递职位
```http
POST /api/application/apply
Content-Type: application/json

{
  "studentId": 1,
  "jobId": 1
}
```

**返回示例:**
```json
{
  "code": 200,
  "message": "成功",
  "data": {
    "id": 1,
    "studentId": 1,
    "jobId": 1,
    "status": 0,
    "applyTime": "2024-12-01T11:20:00"
  }
}
```

### 4.2 获取学生的投递记录
```http
GET /api/application/student/1
```

### 4.3 获取企业收到的投递
```http
GET /api/application/company/1
```

**返回示例:**
```json
{
  "code": 200,
  "message": "成功",
  "data": [
    {
      "id": 1,
      "studentId": 1,
      "studentName": "张三",
      "jobId": 1,
      "jobTitle": "Java开发工程师",
      "companyName": "阿里巴巴",
      "status": 0,
      "applyTime": "2024-12-01T10:00:00"
    }
  ]
}
```

### 4.4 更新投递状态
```http
PUT /api/application/1/status
Content-Type: application/json

{
  "status": 2
}
```

状态说明：
- 0: 待处理
- 1: 已查看
- 2: 面试中
- 3: 已录用
- 4: 已拒绝

### 4.5 标记为已查看
```http
PUT /api/application/1/view
```

### 4.6 通知面试
```http
PUT /api/application/1/interview
```

### 4.7 录用候选人
```http
PUT /api/application/1/hire
```

### 4.8 拒绝候选人
```http
PUT /api/application/1/reject
```

---

## 5. 管理员模块

### 5.1 管理员登录
```http
POST /api/admin/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

### 5.2 创建管理员
```http
POST /api/admin
Content-Type: application/json

{
  "username": "admin2",
  "password": "123456",
  "role": "ADMIN"
}
```

### 5.3 获取就业率统计
```http
GET /api/admin/statistics/employmentRate
```

**返回示例:**
```json
{
  "code": 200,
  "message": "成功",
  "data": {
    "rate": 0.0
  }
}
```

### 5.4 修改管理员密码
```http
PUT /api/admin/updatePassword
Content-Type: application/json

{
  "id": "1",
  "oldPassword": "admin123",
  "newPassword": "newpassword123"
}
```

---

## 6. 公告模块

### 6.1 获取所有公告
```http
GET /api/announcement/list
```

### 6.2 获取显示的公告
```http
GET /api/announcement/active
```

### 6.3 发布公告
```http
POST /api/announcement
Content-Type: application/json

{
  "title": "关于2024届毕业生就业工作的通知",
  "content": "各位同学，请及时关注招聘信息...",
  "adminId": 1
}
```

### 6.4 更新公告
```http
PUT /api/announcement/1
Content-Type: application/json

{
  "title": "更新后的标题",
  "content": "更新后的内容",
  "status": 1
}
```

### 6.5 隐藏公告
```http
PUT /api/announcement/1/hide
```

### 6.6 显示公告
```http
PUT /api/announcement/1/show
```

### 6.7 删除公告
```http
DELETE /api/announcement/1
```

---

## 测试流程示例

### 完整业务流程测试

#### 1. 学生注册并投递简历
```bash
# 1. 注册学生账号
POST /api/student/register
{
  "name": "测试学生",
  "email": "test@student.com",
  "password": "123456",
  "major": "计算机科学"
}

# 2. 学生登录
POST /api/student/login
{
  "email": "test@student.com",
  "password": "123456"
}

# 3. 浏览职位列表
GET /api/job/list

# 4. 投递职位
POST /api/application/apply
{
  "studentId": 3,
  "jobId": 1
}

# 5. 查看投递记录
GET /api/application/student/3
```

#### 2. 企业发布职位并处理简历
```bash
# 1. 企业登录
POST /api/company/login
{
  "email": "hr@alibaba.com",
  "password": "admin123"
}

# 2. 发布职位
POST /api/job
{
  "title": "Python开发工程师",
  "description": "负责后端开发",
  "salary": "15K-25K",
  "location": "杭州",
  "companyId": 1
}

# 3. 查看收到的简历
GET /api/application/company/1

# 4. 通知面试
PUT /api/application/1/interview

# 5. 录用候选人
PUT /api/application/1/hire
```

#### 3. 管理员审核与统计
```bash
# 1. 管理员登录
POST /api/admin/login
{
  "username": "admin",
  "password": "admin123"
}

# 2. 查看待审核企业
GET /api/company/pending

# 3. 审核通过企业
PUT /api/company/2/approve

# 4. 查看就业率
GET /api/admin/statistics/employmentRate

# 5. 发布公告
POST /api/announcement
{
  "title": "招聘会通知",
  "content": "本周五举办校园招聘会",
  "adminId": 1
}
```

---

## 错误响应示例

```json
{
  "code": 500,
  "message": "邮箱已被注册",
  "data": null
}
```

常见错误码：
- 200: 成功
- 500: 服务器错误
- 其他自定义错误码可在代码中定义

---

## cURL命令示例

### 学生登录
```bash
curl -X POST http://localhost:8080/api/student/login \
  -H "Content-Type: application/json" \
  -d '{"email":"zhangsan@example.com","password":"admin123"}'
```

### 获取职位列表
```bash
curl http://localhost:8080/api/job/list
```

### 投递职位
```bash
curl -X POST http://localhost:8080/api/application/apply \
  -H "Content-Type: application/json" \
  -d '{"studentId":1,"jobId":1}'
```

---

## Postman Collection

可以将上述所有接口导入Postman创建测试集合，方便批量测试。

**导入步骤:**
1. 打开Postman
2. 点击Import
3. 选择Raw Text
4. 复制上述API定义
5. 转换为Postman格式

---

## 注意事项
1. 所有时间格式为：`yyyy-MM-dd HH:mm:ss`
2. 密码会自动使用BCrypt加密
3. Token机制已简化，实际使用时需要完善JWT验证
4. 文件上传接口需使用 `multipart/form-data`
5. 删除操作会级联删除相关数据（外键约束）
