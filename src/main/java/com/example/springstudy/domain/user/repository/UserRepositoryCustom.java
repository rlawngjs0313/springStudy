package com.example.springstudy.domain.user.repository;

import com.example.springstudy.domain.user.dto.UserResDTO;

public interface UserRepositoryCustom {
    UserResDTO.MyPageDTO findMyPage(Long userId);
}
