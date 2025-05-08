package com.cybersec.cs.cwnu.cybersec.modules.cms.controller;

import com.cybersec.cs.cwnu.cybersec.modules.auth.model.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    // 获取当前用户信息
    @GetMapping("/api/me")
    public String currentUser(@AuthenticationPrincipal CustomUserDetails user) {
        return String.format("""
            用户ID: %d
            用户名: %s
            角色: %s
            """,
                user.getUserId(),
                user.getUsername(),
                user.getAuthorities()
        );
    }
}