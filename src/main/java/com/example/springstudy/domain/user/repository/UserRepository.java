package com.example.springstudy.domain.user.repository;

import com.example.springstudy.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(@NotBlank @Email String email);
}
