package com.example.springstudy.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;

public class UserReqDTO {

    public record SignUp(
            @NotNull
            String email,
            String password
    ) {}
    public record SignIn(
            String email,
            String password
    ) {}
}
