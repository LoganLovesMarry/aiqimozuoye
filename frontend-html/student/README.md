# 学生系统

## 📁 文件结构

```
student/
├── dashboard.html      # 学生控制台
├── jobs.html          # 浏览职位
├── applications.html  # 我的投递
├── resume.html        # 我的简历
├── profile.html       # 个人信息
└── README.md          # 本文档
```

## 🎨 样式文件

所有样式文件位于 `../css/` 目录：

- **style.css** - 全局基础样式
- **student.css** - 学生系统专用样式
- **icons.css** - 自定义图标样式

## ✨ 功能特性

### 1. 学生控制台 (dashboard.html)
- ✅ 数据统计（投递中、面试邀请、录用通知、浏览职位）
- ✅ 快速操作入口
- ✅ 最近投递列表
- ✅ 推荐职位展示

### 2. 浏览职位 (jobs.html)
- ✅ 职位列表展示
- ✅ 搜索和筛选功能
- ✅ 职位详情查看（模态框）
- ✅ 一键投递简历
- ✅ 职位卡片悬停效果

### 3. 我的投递 (applications.html)
- ✅ 投递记录列表
- ✅ 按状态筛选（待查看/已查看/面试中/已录用/已拒绝）
- ✅ 查看投递详情
- ✅ 撤回投递功能
- ✅ 面试时间提醒
- ✅ 拒绝原因展示

### 4. 我的简历 (resume.html)
- ✅ 简历完善度显示
- ✅ 上传简历文件
- ✅ 编辑/预览简历
- ✅ 基本信息展示
- ✅ 教育经历
- ✅ 项目经验
- ✅ 技能特长

### 5. 个人信息 (profile.html)
- ✅ 个人资料展示和编辑
- ✅ 基本信息管理
- ✅ 修改密码功能
- ✅ 账号设置
- ✅ 通知偏好设置

## 🎯 设计特点

### 紫色渐变主题
- **主色调**: 紫色渐变 (#667eea → #764ba2)
- **风格**: 年轻、活力、专业
- **特色**: 卡片式布局，清晰的视觉层次

### 纯CSS实现
- ❌ 不使用 Bootstrap
- ❌ 不使用任何CSS框架
- ✅ 使用原生CSS Grid和Flexbox
- ✅ 完全自定义设计

### 图标系统
- ❌ 不使用 Bootstrap Icons
- ❌ 不使用 Font Awesome
- ✅ 使用Unicode表情符号
- ✅ 轻量级，无需加载外部资源

### 响应式设计
- ✅ 桌面端优先
- ✅ 平板适配
- ✅ 移动端支持
- ✅ 自适应布局

### 代码组织
- ✅ HTML和CSS完全分离
- ✅ 样式文件模块化
- ✅ 代码清晰易维护

## 🚀 使用方法

### 1. 直接打开
```
在浏览器中直接打开任意HTML文件
```

### 2. 本地服务器（推荐）
```bash
# 使用 Python
cd e:\idea-workspace\aiqimozuoye-1\frontend-html
python -m http.server 8000

# 访问
http://localhost:8000/student/dashboard.html
```

### 3. Live Server
- 使用 VS Code 的 Live Server 插件
- 右键点击HTML文件
- 选择 "Open with Live Server"

## 📝 页面导航

### 侧边栏菜单
所有页面共享统一的侧边栏导航：

- **📊 控制台** → dashboard.html
- **💼 浏览职位** → jobs.html
- **📄 我的投递** → applications.html
- **📋 我的简历** → resume.html
- **👤 个人信息** → profile.html

### 顶部导航栏
- 显示学生姓名
- 退出登录按钮

## 🔐 权限验证

所有页面都包含登录检查：
```javascript
// 检查登录状态
const user = getCurrentUser();
const role = getUserRole();
if (!user || role !== 'student') {
  window.location.href = '../login.html?role=student';
  return;
}
```

## 🎨 自定义样式

### 主题颜色
```css
primary: #667eea     /* 主色 - 紫色 */
secondary: #764ba2   /* 辅助色 - 深紫 */
success: #2ecc71     /* 成功 - 绿色 */
warning: #f39c12     /* 警告 - 橙色 */
danger: #e74c3c      /* 危险 - 红色 */
```

### 修改样式
所有样式都在 `../css/student.css` 中定义，可以根据需要修改：

```css
/* 修改侧边栏颜色 */
.sidebar {
  background: linear-gradient(180deg, #667eea 0%, #764ba2 100%);
}

/* 修改卡片样式 */
.stat-card {
  border-radius: 10px;
  box-shadow: 0 2px 15px rgba(0,0,0,0.08);
}
```

## 📱 响应式断点

```css
/* 平板 */
@media (max-width: 768px) {
  .sidebar { width: 200px; }
}

/* 手机 */
@media (max-width: 576px) {
  .sidebar { width: 100%; }
  .dashboard-container { flex-direction: column; }
}
```

## 🔧 API集成

页面已预留API调用位置，需要后端配合：

```javascript
// 示例：加载职位列表
async function loadJobs() {
  try {
    const jobs = await jobApi.getAll();
    // 渲染职位列表...
  } catch (error) {
    console.error('加载失败:', error);
  }
}
```

## 📊 数据模拟

当前使用模拟数据进行演示：
- 投递统计：使用固定数字
- 职位列表：6个示例职位
- 投递记录：5个示例记录
- 简历信息：使用占位数据

连接后端API后，这些数据将从服务器动态加载。

## 🐛 调试说明

### 查看控制台
打开浏览器开发者工具（F12）查看：
- 网络请求
- JavaScript错误
- 控制台日志

### 常见问题

**问题1：图标显示为方框**
- 确保使用支持表情符号的浏览器
- Chrome、Firefox、Edge最新版均支持

**问题2：样式不生效**
- 检查CSS文件路径是否正确
- 确保所有CSS文件都已加载

**问题3：页面布局错乱**
- 清除浏览器缓存
- 确认CSS文件完整

## 📄 功能说明

### 投递状态
- **待查看** (0)：企业未查看
- **已查看** (1)：企业已查看
- **面试中** (2)：收到面试邀请
- **已录用** (3)：获得录用通知
- **已拒绝** (4)：投递被拒绝

### 简历完善度
系统会根据以下内容计算简历完善度：
- 基本信息（20%）
- 教育经历（20%）
- 项目经验（30%）
- 技能特长（20%）
- 自我评价（10%）

### 职位推荐
系统会根据以下信息推荐合适的职位：
- 专业匹配度
- 技能匹配度
- 地域偏好
- 薪资期望

## 🎓 开发指南

### 添加新页面
1. 在 `student/` 目录创建新HTML文件
2. 引入必要的CSS文件：
   ```html
   <link rel="stylesheet" href="../css/style.css">
   <link rel="stylesheet" href="../css/icons.css">
   <link rel="stylesheet" href="../css/student.css">
   ```
3. 复制侧边栏和导航栏代码
4. 添加认证检查脚本
5. 在侧边栏菜单中添加链接

### 添加新功能
1. 在现有页面中添加UI元素
2. 编写JavaScript处理函数
3. 调用相应的API接口
4. 处理响应和错误

## 📞 技术支持

如有问题或建议，请查看项目文档或联系技术支持。

---

**最后更新**: 2025-12-05
**版本**: 1.0.0
**作者**: System Development Team
