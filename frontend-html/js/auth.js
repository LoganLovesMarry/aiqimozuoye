// 认证相关工具函数

// 保存认证信息
function saveAuth(user, token, role) {
  localStorage.setItem('auth_token', token);
  localStorage.setItem('user', JSON.stringify(user));
  localStorage.setItem('user_role', role);
}

// 清除认证信息
function clearAuth() {
  localStorage.removeItem('auth_token');
  localStorage.removeItem('user');
  localStorage.removeItem('user_role');
}

// 获取当前用户
function getCurrentUser() {
  const user = localStorage.getItem('user');
  return user ? JSON.parse(user) : null;
}

// 获取认证令牌
function getAuthToken() {
  return localStorage.getItem('auth_token');
}

// 获取用户角色
function getUserRole() {
  return localStorage.getItem('user_role');
}

// 检查是否已登录
function isAuthenticated() {
  return !!getAuthToken();
}

// 检查页面访问权限
function checkAuth(requiredRole) {
  if (!isAuthenticated()) {
    // 保存当前URL，登录后跳转回来
    sessionStorage.setItem('redirect_after_login', window.location.pathname);
    window.location.href = '../login.html';
    return false;
  }
  
  const role = getUserRole();
  if (requiredRole && role !== requiredRole) {
    alert('无权访问此页面');
    window.location.href = '../index.html';
    return false;
  }
  
  return true;
}

// 退出登录
function logout() {
  if (confirm('确定要退出登录吗？')) {
    clearAuth();
    window.location.href = '../index.html';
  }
}

// 页面加载时显示用户信息
function displayUserInfo() {
  const user = getCurrentUser();
  const userInfoElements = document.querySelectorAll('.user-name, #companyName, #adminName');
  
  if (user && userInfoElements.length > 0) {
    userInfoElements.forEach(el => {
      el.textContent = user.name || user.username || user.companyName || '用户';
    });
  }
}

// 初始化页面（检查登录状态并显示用户信息）
function initPage(requiredRole) {
  if (!checkAuth(requiredRole)) {
    return false;
  }
  
  // 检查角色是否匹配
  const role = getUserRole();
  if (requiredRole && role !== requiredRole) {
    alert('您没有权限访问该页面');
    window.location.href = `../${role}/dashboard.html`;
    return false;
  }
  
  displayUserInfo();
  return true;
}

// 处理登录重定向
function handleLoginRedirect() {
  const redirectUrl = sessionStorage.getItem('redirect_after_login');
  if (redirectUrl) {
    sessionStorage.removeItem('redirect_after_login');
    window.location.href = redirectUrl;
  } else {
    const role = getUserRole();
    window.location.href = role ? `../${role}/dashboard.html` : '../index.html';
  }
}

// 检查登录状态，未登录则跳转到登录页
function requireAuth(requiredRole) {
  if (!isAuthenticated()) {
    const currentPath = window.location.pathname;
    sessionStorage.setItem('redirect_after_login', currentPath);
    window.location.href = `../login.html?role=${requiredRole || 'student'}`;
    return false;
  }
  
  const role = getUserRole();
  if (requiredRole && role !== requiredRole) {
    alert('您没有权限访问该页面');
    window.location.href = `../${role}/dashboard.html`;
    return false;
  }
  
  return true;
}
