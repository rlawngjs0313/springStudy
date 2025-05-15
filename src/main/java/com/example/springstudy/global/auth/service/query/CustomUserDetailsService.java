package com.example.springstudy.global.auth.service.query;

import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new UserException(UserErrorCode.NOT_FOUND));
        return new CustomUserDetails(user);
    }
}
