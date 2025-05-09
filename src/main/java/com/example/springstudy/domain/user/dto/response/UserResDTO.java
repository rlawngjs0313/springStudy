package com.example.springstudy.domain.user.dto.response;

import lombok.Builder;

public class UserResDTO {

    @Builder
    public record UserLogin(String accessToken, String refreshToken){}
}
