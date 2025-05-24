package com.example.springstudy.domain.user.converter;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.entity.UserFavorite;
import com.example.springstudy.domain.user.entity.UserInfo;
import com.example.springstudy.domain.user.enums.Food;
import com.example.springstudy.domain.user.enums.Gender;
import com.example.springstudy.global.auth.enums.Role;

public class UserConverter {

    // email, pwd, role -> User
    public static User toUser(UserReqDTO.SignUp dto, String pwd) {
        return User.builder()
                .email(dto.email())
                .pwd(pwd)
                .role(dto.role())
                .build();
    }

    // User Info -> UserInfo
    public static UserInfo toUserInfo(UserReqDTO.SignUp dto, User user) {
        return UserInfo.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birthDate())
                .address(dto.address())
                .specAddress(dto.specAddress())
                .user(user)
                .build();
    }

    // UserFavorite
    public static UserFavorite toUserFavorite(Food food, User user){
        return UserFavorite.builder()
                .food(food)
                .user(user)
                .build();
    }

    // accessToken, refreshToken -> UserLoginDTO
    public static UserResDTO.UserLogin toUserLongin(String accessToken, String refreshToken){
        return UserResDTO.UserLogin.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // User -> UserInfoDTO
    public static UserResDTO.UserInfo toUserInfoDTO(
            String email,
            UserInfo userInfo
    ){
        return UserResDTO.UserInfo.builder()
                .email(email)
                .name(userInfo.getName())
                .gender(userInfo.getGender())
                .build();
    }
}
