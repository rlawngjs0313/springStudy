package com.example.springstudy.global.auth.service.command;

import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;

public interface TokenCommandService {
    UserResDTO.UserLogin createToken(User user);
}
