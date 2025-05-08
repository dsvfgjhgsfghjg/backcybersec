package com.cybersec.cs.cwnu.cybersec.modules.auth.service;

import com.cybersec.cs.cwnu.cybersec.modules.auth.model.CustomUserDetails;
import com.cybersec.cs.cwnu.cybersec.modules.auth.model.User;
import com.cybersec.cs.cwnu.cybersec.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户不存在: " + username));

        // 将角色转换为 GrantedAuthority
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getId(), // 注入用户ID
                user.getUsername(),
                user.getPassword(),
                true,        // 账户启用状态（根据业务逻辑调整）
                true,        // 账户未过期
                true,        // 凭证未过期
                true,        // 账户未锁定
                authorities
        );
    }
}