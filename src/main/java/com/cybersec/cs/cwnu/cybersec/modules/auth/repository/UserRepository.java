package com.cybersec.cs.cwnu.cybersec.modules.auth.repository;

import com.cybersec.cs.cwnu.cybersec.modules.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名查找用户
    Optional<User> findByUsername(String username);
}