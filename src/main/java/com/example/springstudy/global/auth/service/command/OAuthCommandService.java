package com.example.springstudy.global.auth.service.command;

import com.example.springstudy.domain.user.dto.response.UserResDTO;

public interface OAuthCommandService {
    UserResDTO.UserLogin login(String code);
}
