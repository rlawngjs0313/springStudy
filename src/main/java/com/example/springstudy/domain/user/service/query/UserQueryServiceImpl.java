package com.example.springstudy.domain.user.service.query;


import com.example.springstudy.domain.user.converter.UserConverter;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.entity.UserInfo;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserInfoReposiroty;
import com.example.springstudy.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserInfoReposiroty userInfoReposiroty;

    @Override
    public User getAuthUserId() {

        // 유저 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(()->
                    new UserException(UserErrorCode.NOT_FOUND));
        } else throw new UserException(UserErrorCode.UNAUTH);
    }

    @Override
    public UserResDTO.UserInfo getUserInfo(
            org.springframework.security.core.userdetails.User user
    ) {
        User userEntity = userRepository.findUserByEmail(user.getUsername()).orElseThrow(()->
                new UserException(UserErrorCode.NOT_FOUND));

        UserInfo userInfo = userInfoReposiroty.findByUserId(userEntity.getId()).orElseThrow(()->
                new UserException(UserErrorCode.NOT_FOUND));

        return UserConverter.toUserInfoDTO(user.getUsername(), userInfo);
    }
}
