package com.employment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Spring Security 配置类
 * 配置安全策略、跨域访问、密码加密等
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * 密码加密器
     * 使用BCrypt加密算法
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 配置HTTP安全
     * 禁用CSRF、配置跨域、设置无状态会话、允许所有请求访问
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 禁用CSRF保护（前后端分离项目通常禁用）
            .csrf().disable()
            
            // 启用跨域资源共享
            .cors().and()
            
            // 配置会话管理为无状态（使用JWT不需要session）
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            
            // 配置请求授权
            .authorizeRequests()
            // 允许访问登录和注册接口
            .antMatchers(
                "/student/login",
                "/student/register",
                "/company/login",
                "/company/register",
                "/admin/login",
                "/admin/register",
                "/announcement/active",
                "/job/all",
                "/job/search",
                "/druid/**"
            ).permitAll()
            // 其他所有请求都需要认证（暂时也放开，后续可以添加JWT过滤器）
            .anyRequest().permitAll()
            .and()
            
            // 禁用缓存
            .headers().cacheControl();
        
        return http.build();
    }
    
    /**
     * 配置跨域资源共享(CORS)
     * 允许前端应用跨域访问后端API
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 允许的源（开发环境）
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        
        // 允许的HTTP方法
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        
        // 允许的请求头
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        // 允许携带认证信息（如cookies）
        configuration.setAllowCredentials(true);
        
        // 暴露的响应头
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        
        // 预检请求的有效期（秒）
        configuration.setMaxAge(3600L);
        
        // 将配置应用到所有路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
