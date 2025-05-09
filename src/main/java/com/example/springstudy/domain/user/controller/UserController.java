package com.example.springstudy.domain.user.controller;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.exception.code.UserSuccessCode;
import com.example.springstudy.domain.user.service.command.UserCommandServiceImpl;
import com.example.springstudy.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Transactional
public class UserController {

    private final UserCommandServiceImpl userCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<Void> signUp(
            @Valid @RequestBody UserReqDTO.SignUp dto
    ) {
        userCommandService.signUp(dto);
        UserSuccessCode code = UserSuccessCode.SIGN_UP;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), null);
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.UserLogin> login(
            @Valid @RequestBody UserReqDTO.SignIn dto
    ) {
        UserResDTO.UserLogin userLogin = userCommandService.login(dto);
        UserSuccessCode code = UserSuccessCode.LOGIN;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), userLogin);
    }
}
