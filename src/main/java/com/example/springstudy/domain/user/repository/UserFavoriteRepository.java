package com.example.springstudy.domain.user.repository;

import com.example.springstudy.domain.user.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {
}
