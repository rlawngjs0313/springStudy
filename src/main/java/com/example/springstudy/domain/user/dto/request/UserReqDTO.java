package com.example.springstudy.domain.user.dto.request;

import com.example.springstudy.domain.user.enums.Food;
import com.example.springstudy.domain.user.enums.Gender;
import com.example.springstudy.global.auth.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserReqDTO {

    @Builder
    public record SignUp(
            @NotBlank
            String name,
            @NotBlank @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            LocalDate birthDate,
            @Size(min = 5, max = 12)
            String address,
            @Size(min = 5, max = 12)
            String specAddress,
            List<Food> preferCategory,
            @NotNull
            Role role
    ) {}
    public record SignIn(
            String email,
            String password
    ) {}
}
