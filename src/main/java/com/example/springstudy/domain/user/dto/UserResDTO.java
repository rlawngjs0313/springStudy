package com.example.springstudy.domain.user.dto;

import lombok.Builder;

public class UserResDTO {

    @Builder
    public record MyPageDTO(String nickname, String email, String phone, int userPoint) {}
}
