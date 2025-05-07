package com.example.springstudy.domain.user.converter;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;

public class UserConverter {

    // email, pwd -> User
    public static User toUser(String email, String pwd) {
        return User.builder()
                .email(email)
                .pwd(pwd)
                .build();
    }

    // accessToken, refreshToken -> UserLoginDTO
    public static UserResDTO.UserLogin toUserLongin(String accessToken, String refreshToken){
        return UserResDTO.UserLogin.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
