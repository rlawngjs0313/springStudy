package com.example.springstudy.domain.user.service.query;

import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;

public interface UserQueryService {
    User getAuthUserId();

    UserResDTO.UserInfo getUserInfo(
            org.springframework.security.core.userdetails.User user
    );
}
