package com.example.springstudy.domain.user.service.query;


import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
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

    public User getAuthUserId() {

        // 유저 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userRepository.findUserByEmail(userDetails.getUsername()).orElseThrow(()->
                    new UserException(UserErrorCode.NOT_FOUND));
        } else throw new UserException(UserErrorCode.UNAUTH);
    }
}
