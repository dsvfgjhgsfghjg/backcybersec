package com.cybersec.cs.cwnu.cybersec.config; // 确保包路径一致

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component // 确保能被 Spring 扫描到
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        // 1. 从请求头获取 JWT
        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            // 2. 去掉 Bearer 前缀
            String jwt = token.substring(7);

            // 3. 验证 Token 的逻辑（需自行实现）
            if (validateToken(jwt)) {
                // 4. 创建 Authentication 对象
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        getUsernameFromToken(jwt), // 从 Token 中提取用户名
                        null,
                        getAuthoritiesFromToken(jwt) // 从 Token 中提取权限
                );

                // 5. 将认证信息存入 SecurityContext
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }

    // 以下方法需要根据实际 JWT 库实现
    private boolean validateToken(String jwt) {
        // 实现 Token 验证逻辑
        return true; // 示例返回
    }

    private String getUsernameFromToken(String jwt) {
        // 实现用户名提取
        return "user"; // 示例返回
    }

    private Collection<? extends GrantedAuthority> getAuthoritiesFromToken(String jwt) {
        // 实现权限提取
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // 示例返回
    }
}