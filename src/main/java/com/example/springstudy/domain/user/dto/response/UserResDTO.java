package com.example.springstudy.domain.user.dto.response;

import com.example.springstudy.domain.user.enums.Gender;
import lombok.Builder;

public class UserResDTO {

    @Builder
    public record UserLogin(String accessToken, String refreshToken){}

    @Builder
    public record UserInfo(
            String name,
            String email,
            Gender gender
    ) {}
}
