package com.example.springstudy.domain.user.service.command;

import com.example.springstudy.domain.user.converter.UserConverter;
import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.service.command.TokenCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenCommandService tokenCommandService;

    public void signUp(UserReqDTO.SignUp dto) {
        String pwd = passwordEncoder.encode(dto.password());
        userRepository.save(UserConverter.toUser(dto.email(), pwd));
    }

    public UserResDTO.UserLogin login(UserReqDTO.SignIn dto){
        User user = userRepository.findUserByEmail(dto.email()).orElseThrow(() ->
                new UserException(UserErrorCode.NOT_FOUND));
        if (!passwordEncoder.matches(dto.password(), user.getPwd())){
            throw new UserException(UserErrorCode.WRONG_ID_OR_PWD);
        }
        return tokenCommandService.createToken(user);
    }
}
