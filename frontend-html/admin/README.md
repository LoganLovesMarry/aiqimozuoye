# 管理员系统

## 📁 文件结构

```
admin/
├── dashboard.html      # 管理员控制台
├── users.html         # 用户管理
├── companies.html     # 企业管理
├── jobs.html          # 职位审核
├── statistics.html    # 数据统计
├── system.html        # 系统设置
└── README.md          # 本文档
```

## 🎨 样式文件

所有样式文件位于 `../css/` 目录：

- **style.css** - 全局基础样式
- **admin.css** - 管理员系统专用样式
- **icons.css** - 自定义图标样式

## ✨ 功能特性

### 1. 管理员控制台 (dashboard.html)
- ✅ 核心数据统计（学生、企业、职位、就业率）
- ✅ 快速操作入口
- ✅ 待处理事项列表
- ✅ 系统活动时间线

### 2. 用户管理 (users.html)
- ✅ 用户列表展示
- ✅ 搜索和筛选功能
- ✅ 查看用户详情
- ✅ 编辑/删除用户
- ✅ 按角色和状态筛选

### 3. 企业管理 (companies.html)
- ✅ 企业认证审核
- ✅ 已认证企业列表
- ✅ 企业详情查看
- ✅ 企业账号暂停/恢复
- ✅ 统计数据展示

### 4. 职位审核 (jobs.html)
- ✅ 待审核职位列表
- ✅ 职位审核（通过/拒绝）
- ✅ 所有职位管理
- ✅ 职位详情查看
- ✅ 职位下架功能

### 5. 数据统计 (statistics.html)
- ✅ 核心指标展示
- ✅ 用户增长趋势图（占位符）
- ✅ 就业数据分析图（占位符）
- ✅ 专业分布统计
- ✅ 热门职位TOP10

### 6. 系统设置 (system.html)
- ✅ 基本设置（系统名称、邮箱等）
- ✅ 审核设置
- ✅ 安全设置
- ✅ 邮件设置
- ✅ 数据备份和清理
- ✅ 系统信息查看

## 🎯 技术特点

### 纯CSS实现
- ❌ 不使用 Bootstrap
- ❌ 不使用第三方CSS框架
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
http://localhost:8000/admin/dashboard.html
```

### 3. Live Server
- 使用 VS Code 的 Live Server 插件
- 右键点击HTML文件
- 选择 "Open with Live Server"

## 📝 页面导航

### 侧边栏菜单
所有页面共享统一的侧边栏导航：

- **📊 控制台** → dashboard.html
- **👥 用户管理** → users.html
- **🏢 企业管理** → companies.html
- **💼 职位审核** → jobs.html
- **📈 数据统计** → statistics.html
- **⚙️ 系统设置** → system.html

### 顶部导航栏
- 显示管理员名称
- 退出登录按钮

## 🔐 权限验证

所有页面都包含登录检查：
```javascript
// 检查登录状态
const user = getCurrentUser();
const role = getUserRole();
if (!user || role !== 'admin') {
  window.location.href = '../login.html?role=admin';
  return;
}
```

## 🎨 自定义样式

### 主题颜色
```css
primary: #1a1a2e     /* 深色主题 */
accent: #4ecca3      /* 强调色 - 绿色 */
secondary: #16213e   /* 辅助色 */
```

### 修改样式
所有样式都在 `../css/admin.css` 中定义，可以根据需要修改：

```css
/* 修改侧边栏颜色 */
.sidebar {
  background-color: #1a1a2e; /* 改为你想要的颜色 */
}

/* 修改卡片样式 */
.stat-card {
  border-radius: 10px; /* 调整圆角 */
  box-shadow: 0 2px 15px rgba(0,0,0,0.08); /* 调整阴影 */
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
// 示例：加载用户列表
async function loadUsers() {
  try {
    const users = await adminApi.getUsers();
    // 渲染用户列表...
  } catch (error) {
    console.error('加载失败:', error);
  }
}
```

## 📊 数据模拟

当前使用模拟数据进行演示：
- 用户列表：2个示例用户
- 企业列表：2个示例企业
- 职位列表：2个示例职位
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

## 📄 权限说明

### 管理员权限
管理员拥有系统的所有权限：
- ✅ 查看所有数据
- ✅ 管理用户账号
- ✅ 审核企业认证
- ✅ 审核职位发布
- ✅ 查看统计报表
- ✅ 修改系统设置

### 安全建议
1. **定期修改密码**
2. **启用双因素认证**（如果支持）
3. **限制管理员账号数量**
4. **记录操作日志**
5. **定期备份数据**

## 🎓 开发指南

### 添加新页面
1. 在 `admin/` 目录创建新HTML文件
2. 引入必要的CSS文件：
   ```html
   <link rel="stylesheet" href="../css/style.css">
   <link rel="stylesheet" href="../css/icons.css">
   <link rel="stylesheet" href="../css/admin.css">
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

如有问题或建议，请联系技术支持。

---

**最后更新**: 2024-12-05
**版本**: 1.0.0
**作者**: System Development Team
