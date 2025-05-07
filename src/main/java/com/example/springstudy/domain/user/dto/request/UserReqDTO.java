package com.example.springstudy.domain.user.dto.request;

public class UserReqDTO {

    public record SignUp(String email, String password) {}
    public record SignIn(String email, String password) {}
}
