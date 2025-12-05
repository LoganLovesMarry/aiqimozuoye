// API 基础配置和工具函数
const API_BASE_URL = 'http://localhost:8080/api';

// 获取存储的 token
function getToken() {
  return localStorage.getItem('auth_token');
}

// 获取存储的用户信息
function getCurrentUser() {
  const userStr = localStorage.getItem('user');
  return userStr ? JSON.parse(userStr) : null;
}

// 获取用户角色
function getUserRole() {
  return localStorage.getItem('user_role');
}

// 保存认证信息
function saveAuth(user, token, role) {
  localStorage.setItem('user', JSON.stringify(user));
  localStorage.setItem('auth_token', token);
  localStorage.setItem('user_role', role);
}

// 清除认证信息
function clearAuth() {
  localStorage.removeItem('user');
  localStorage.removeItem('auth_token');
  localStorage.removeItem('user_role');
}

// 检查是否已登录
function isAuthenticated() {
  return !!getToken();
}

// 通用 API 请求函数
async function apiRequest(endpoint, options = {}) {
  const url = `${API_BASE_URL}${endpoint}`;
  
  const headers = {
    'Content-Type': 'application/json',
    ...options.headers
  };
  
  const token = getToken();
  if (token) {
    headers['Authorization'] = `Bearer ${token}`;
  }
  
  const config = {
    ...options,
    headers,
    credentials: 'include' // 确保发送cookies
  };
  
  // 处理请求体，如果是FormData则不设置Content-Type，让浏览器自动设置
  if (config.body && !(config.body instanceof FormData)) {
    config.body = JSON.stringify(config.body);
  } else if (config.body instanceof FormData) {
    delete headers['Content-Type'];
  }
  
  try {
    const response = await fetch(url, config);
    
    // 处理无响应体的响应
    if (response.status === 204) {
      return { code: 204, message: '操作成功' };
    }
    
    // 尝试解析JSON响应
    let data;
    const contentType = response.headers.get('content-type');
    if (contentType && contentType.includes('application/json')) {
      data = await response.json();
    } else {
      const text = await response.text();
      throw new Error(`非JSON响应: ${text}`);
    }
    
    // 处理认证错误
    if (response.status === 401) {
      clearAuth();
      window.location.href = '../login.html';
      return { code: 401, message: '未授权，请重新登录' };
    }
    
    // 处理其他错误状态码
    if (!response.ok) {
      const error = new Error(data.message || '请求失败');
      error.code = response.status;
      error.data = data;
      throw error;
    }
    
    return data;
  } catch (error) {
    console.error('API 请求失败:', error);
    if (error.code === 401) {
      clearAuth();
      window.location.href = '../login.html';
    }
    throw error;
  }
}

// 学生 API
const studentApi = {
  register: (data) => apiRequest('/student/register', {
    method: 'POST',
    body: JSON.stringify(data)
  }),
  
  login: (email, password) => apiRequest('/student/login', {
    method: 'POST',
    body: JSON.stringify({ email, password })
  }),
  
  getById: (id) => apiRequest(`/student/${id}`),
  
  getAll: () => apiRequest('/student/all'),
  
  update: (id, data) => apiRequest(`/student/${id}`, {
    method: 'PUT',
    body: JSON.stringify(data)
  }),
  
  uploadResume: async (file) => {
    const formData = new FormData();
    formData.append('file', file);
    
    const token = getToken();
    const response = await fetch(`${API_BASE_URL}/student/resume/upload`, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${token}`
      },
      body: formData
    });
    
    return response.json();
  },
  
  delete: (id) => apiRequest(`/student/${id}`, { method: 'DELETE' })
};

// 企业 API
const companyApi = {
  // 企业注册
  register: (data) => apiRequest('/company/register', {
    method: 'POST',
    body: data
  }),
  
  // 企业登录
  login: (email, password) => apiRequest('/company/login', {
    method: 'POST',
    body: { email, password }
  }),
  
  // 获取企业信息
  getProfile: () => apiRequest('/company/profile'),
  
  // 更新企业信息
  updateProfile: (data) => apiRequest('/company/profile', {
    method: 'PUT',
    body: data
  }),
  
  // 修改密码
  changePassword: (oldPassword, newPassword) => apiRequest('/company/change-password', {
    method: 'POST',
    body: { oldPassword, newPassword }
  }),
  
  // 获取企业发布的职位
  getJobs: (status = '') => apiRequest(`/company/jobs${status ? `?status=${status}` : ''}`),
  
  // 发布新职位
  postJob: (jobData) => apiRequest('/company/jobs', {
    method: 'POST',
    body: jobData
  }),
  
  // 更新职位信息
  updateJob: (jobId, jobData) => apiRequest(`/company/jobs/${jobId}`, {
    method: 'PUT',
    body: jobData
  }),
  
  // 关闭/开启职位
  toggleJobStatus: (jobId, isActive) => apiRequest(`/company/jobs/${jobId}/status`, {
    method: 'PATCH',
    body: { active: isActive }
  }),
  
  // 获取职位申请
  getJobApplications: (jobId, status = '') => {
    let url = '/company/applications';
    if (jobId) url += `?jobId=${jobId}`;
    if (status) url += `${jobId ? '&' : '?'}status=${status}`;
    return apiRequest(url);
  },
  
  // 处理职位申请
  processApplication: (applicationId, action, feedback = '') => 
    apiRequest(`/company/applications/${applicationId}`, {
      method: 'PATCH',
      body: { action, feedback }
    }),
  
  // 获取统计数据
  getStats: () => apiRequest('/company/stats'),
  
  // 获取消息通知
  getNotifications: (unreadOnly = false) => 
    apiRequest(`/company/notifications${unreadOnly ? '?unread=true' : ''}`),
  
  // 标记通知已读
  markNotificationRead: (notificationId) => 
    apiRequest(`/company/notifications/${notificationId}/read`, { method: 'PATCH' }),
  
  // 搜索人才
  searchTalents: (filters) => apiRequest('/company/talents/search', {
    method: 'POST',
    body: filters
  })
};

// 管理员 API
const adminApi = {
  // 管理员登录
  login: (username, password) => apiRequest('/admin/login', {
    method: 'POST',
    body: { username, password }
  }),
  
  // 获取管理员信息
  getProfile: () => apiRequest('/admin/profile'),
  
  // 修改密码
  changePassword: (oldPassword, newPassword) => apiRequest('/admin/change-password', {
    method: 'POST',
    body: { oldPassword, newPassword }
  }),
  
  // 用户管理
  // 获取用户列表
  getUsers: (params = {}) => {
    const query = new URLSearchParams(params).toString();
    return apiRequest(`/admin/users${query ? `?${query}` : ''}`);
  },
  
  // 创建用户
  createUser: (userData) => apiRequest('/admin/users', {
    method: 'POST',
    body: userData
  }),
  
  // 获取用户详情
  getUser: (userId) => apiRequest(`/admin/users/${userId}`),
  
  // 更新用户信息
  updateUser: (userId, userData) => apiRequest(`/admin/users/${userId}`, {
    method: 'PUT',
    body: userData
  }),
  
  // 禁用/启用用户
  toggleUserStatus: (userId, active) => apiRequest(`/admin/users/${userId}/status`, {
    method: 'PATCH',
    body: { active }
  }),
  
  // 企业管理
  // 获取企业列表
  getCompanies: (params = {}) => {
    const query = new URLSearchParams(params).toString();
    return apiRequest(`/admin/companies${query ? `?${query}` : ''}`);
  },
  
  // 审核企业
  verifyCompany: (companyId, verified, reason = '') => 
    apiRequest(`/admin/companies/${companyId}/verify`, {
      method: 'PATCH',
      body: { verified, reason }
    }),
  
  // 职位管理
  // 获取职位列表
  getJobs: (params = {}) => {
    const query = new URLSearchParams(params).toString();
    return apiRequest(`/admin/jobs${query ? `?${query}` : ''}`);
  },
  
  // 管理职位状态
  manageJob: (jobId, action, reason = '') => 
    apiRequest(`/admin/jobs/${jobId}`, {
      method: 'PATCH',
      body: { action, reason }
    }),
  
  // 数据统计
  // 获取统计数据
  getStats: () => apiRequest('/admin/stats'),
  
  // 获取就业率统计
  getEmploymentStats: (timeRange = 'monthly') => 
    apiRequest(`/admin/stats/employment?range=${timeRange}`),
  
  // 系统设置
  // 获取系统设置
  getSettings: () => apiRequest('/admin/settings'),
  
  // 更新系统设置
  updateSettings: (settings) => apiRequest('/admin/settings', {
    method: 'PUT',
    body: settings
  }),
  
  // 日志管理
  // 获取系统日志
  getLogs: (params = {}) => {
    const query = new URLSearchParams(params).toString();
    return apiRequest(`/admin/logs${query ? `?${query}` : ''}`);
  },
  
  // 备份与恢复
  // 创建备份
  createBackup: () => apiRequest('/admin/backup', { method: 'POST' }),
  
  // 获取备份列表
  getBackups: () => apiRequest('/admin/backups'),
  
  // 恢复备份
  restoreBackup: (backupId) => apiRequest(`/admin/backup/${backupId}/restore`, {
    method: 'POST'
  })
};

// 职位 API
const jobApi = {
  getAll: () => apiRequest('/job/all'),
  
  getById: (id) => apiRequest(`/job/${id}`),
  
  getByCompanyId: (companyId) => apiRequest(`/job/company/${companyId}`),
  
  search: (params) => {
    const query = new URLSearchParams(params).toString();
    return apiRequest(`/job/search?${query}`);
  },
  
  create: (data) => apiRequest('/job', {
    method: 'POST',
    body: JSON.stringify(data)
  }),
  
  update: (id, data) => apiRequest(`/job/${id}`, {
    method: 'PUT',
    body: JSON.stringify(data)
  }),
  
  close: (id) => apiRequest(`/job/${id}/close`, { method: 'PUT' }),
  
  open: (id) => apiRequest(`/job/${id}/open`, { method: 'PUT' }),
  
  delete: (id) => apiRequest(`/job/${id}`, { method: 'DELETE' })
};

// 申请 API
const applicationApi = {
  apply: (studentId, jobId) => apiRequest('/application', {
    method: 'POST',
    body: JSON.stringify({ studentId, jobId })
  }),
  
  getById: (id) => apiRequest(`/application/${id}`),
  
  getByStudentId: (studentId) => apiRequest(`/application/student/${studentId}`),
  
  getByCompanyId: (companyId) => apiRequest(`/application/company/${companyId}`),
  
  updateStatus: (id, status) => apiRequest(`/application/${id}/status`, {
    method: 'PUT',
    body: JSON.stringify({ status })
  }),
  
  view: (id) => apiRequest(`/application/${id}/view`, { method: 'PUT' }),
  
  interview: (id) => apiRequest(`/application/${id}/interview`, { method: 'PUT' }),
  
  hire: (id) => apiRequest(`/application/${id}/hire`, { method: 'PUT' }),
  
  reject: (id) => apiRequest(`/application/${id}/reject`, { method: 'PUT' })
};

// 公告 API
const announcementApi = {
  getAll: () => apiRequest('/announcement/all'),
  
  getActive: () => apiRequest('/announcement/active'),
  
  getById: (id) => apiRequest(`/announcement/${id}`),
  
  create: (data) => apiRequest('/announcement', {
    method: 'POST',
    body: JSON.stringify(data)
  }),
  
  update: (id, data) => apiRequest(`/announcement/${id}`, {
    method: 'PUT',
    body: JSON.stringify(data)
  }),
  
  hide: (id) => apiRequest(`/announcement/${id}/hide`, { method: 'PUT' }),
  
  show: (id) => apiRequest(`/announcement/${id}/show`, { method: 'PUT' }),
  
  delete: (id) => apiRequest(`/announcement/${id}`, { method: 'DELETE' })
};

// 状态文本映射
const ApplicationStatus = {
  0: '待查看',
  1: '已查看',
  2: '面试中',
  3: '已录用',
  4: '已拒绝'
};
