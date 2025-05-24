package com.example.springstudy.global.auth.service.command;

import com.example.springstudy.domain.user.converter.UserConverter;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.global.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenCommandService implements TokenCommandService {

    private final JwtUtil jwtUtil;

    @Override
    public UserResDTO.UserLogin createToken(User user) {
        String accessToken = jwtUtil.createAccessToken(user);
        String refreshToken = jwtUtil.createRefreshToken(user);
        return UserConverter.toUserLongin(accessToken, refreshToken);
    }
}
