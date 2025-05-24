package com.example.springstudy.domain.user.repository;

import com.example.springstudy.domain.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoReposiroty extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserId(Long userId);
}
