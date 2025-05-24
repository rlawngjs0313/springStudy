package com.example.springstudy.domain.user.repository;

import com.example.springstudy.domain.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoReposiroty extends JpaRepository<UserInfo, Long> {
}
