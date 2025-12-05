# 企业管理系统页面

## 📁 文件结构

```
company/
├── dashboard.html      # 企业控制台
├── jobs.html          # 职位管理
├── applications.html  # 应聘管理
├── profile.html       # 企业信息
└── README.md          # 本文档
```

## 🎨 样式文件

所有样式文件位于 `../css/` 目录：

- **style.css** - 全局基础样式
- **company.css** - 企业页面专用样式
- **icons.css** - 自定义图标样式

## ✨ 功能特性

### 1. 企业控制台 (dashboard.html)
- ✅ 数据统计卡片（发布职位数、待处理申请、面试安排、录用人数）
- ✅ 最近动态列表
- ✅ 快速发布职位入口
- ✅ 侧边栏导航

### 2. 职位管理 (jobs.html)
- ✅ 职位列表展示
- ✅ 发布新职位
- ✅ 编辑职位信息
- ✅ 暂停/继续招聘
- ✅ 删除职位
- ✅ 查看申请人数统计

### 3. 应聘管理 (applications.html)
- ✅ 应聘列表展示
- ✅ 状态筛选（全部/待处理/已查看/面试中/已录用/已拒绝）
- ✅ 查看简历详情
- ✅ 更改应聘状态
- ✅ 联系候选人
- ✅ 应聘者信息展示

### 4. 企业信息 (profile.html)
- ✅ 查看/编辑企业基本信息
- ✅ 更新联系方式
- ✅ 修改企业简介
- ✅ 修改登录密码

## 🎯 技术特点

### 纯CSS实现
- ❌ 不使用 Bootstrap
- ❌ 不使用第三方CSS框架
- ✅ 使用原生CSS Grid和Flexbox
- ✅ 完全自定义样式

### 图标系统
- ❌ 不使用 Bootstrap Icons
- ❌ 不使用 Font Awesome
- ✅ 使用Unicode表情符号作为图标
- ✅ 轻量级，无需加载外部字体

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
在浏览器中直接打开任意HTML文件即可
```

### 2. 本地服务器（推荐）
```bash
# 使用 Python
cd e:\idea-workspace\aiqimozuoye-1\frontend-html
python -m http.server 8000

# 访问
http://localhost:8000/company/dashboard.html
```

### 3. Live Server
- 使用 VS Code 的 Live Server 插件
- 右键点击HTML文件
- 选择 "Open with Live Server"

## 📝 页面导航

### 侧边栏菜单
所有页面共享统一的侧边栏导航：

- **📊 控制台** → dashboard.html
- **💼 职位管理** → jobs.html
- **📄 应聘管理** → applications.html
- **👤 企业信息** → profile.html

### 顶部导航栏
- 显示企业名称
- 退出登录按钮

## 🔐 权限验证

所有页面都包含登录检查：
```javascript
// 检查登录状态
const user = getCurrentUser();
if (!user || getUserRole() !== 'company') {
  window.location.href = '../login.html?role=company';
  return;
}
```

## 🎨 自定义样式

### 主题颜色
```css
primary: #3498db    /* 主色调 - 蓝色 */
success: #4caf50    /* 成功 - 绿色 */
warning: #ff9800    /* 警告 - 橙色 */
danger: #f44336     /* 危险 - 红色 */
```

### 修改样式
所有样式都在 `../css/company.css` 中定义，可以根据需要修改：

```css
/* 修改侧边栏颜色 */
.sidebar {
  background-color: #2c3e50; /* 改为你想要的颜色 */
}

/* 修改卡片样式 */
.stat-card {
  border-radius: 8px; /* 调整圆角 */
  box-shadow: 0 2px 10px rgba(0,0,0,0.05); /* 调整阴影 */
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
    // const jobs = await companyApi.getJobs();
    // 渲染职位列表...
  } catch (error) {
    console.error('加载失败:', error);
  }
}
```

## 📊 数据模拟

当前使用模拟数据进行演示：
- 职位列表：2个示例职位
- 应聘列表：3个示例应聘
- 统计数据：使用占位数据

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

## 📄 许可

本项目为教学演示项目，可自由使用和修改。

## 🤝 贡献

欢迎提交问题和改进建议！

---

**最后更新**: 2024-12-05
**版本**: 1.0.0
