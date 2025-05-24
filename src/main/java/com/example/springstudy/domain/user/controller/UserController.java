package com.example.springstudy.domain.user.controller;

import com.example.springstudy.domain.user.dto.request.UserReqDTO;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.exception.code.UserSuccessCode;
import com.example.springstudy.domain.user.service.command.UserCommandServiceImpl;
import com.example.springstudy.domain.user.service.query.UserQueryService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.SuccessStatus;
import com.example.springstudy.global.auth.annotation.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Transactional
public class UserController {

    private final UserCommandServiceImpl userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/sign-up")
    public ApiResponse<Void> signUp(
            @Valid @RequestBody UserReqDTO.SignUp dto
    ) {
        userCommandService.signUp(dto);
        return ApiResponse.created();
    }

    @PostMapping("/login")
    public ApiResponse<UserResDTO.UserLogin> login(
            @Valid @RequestBody UserReqDTO.SignIn dto
    ) {
        UserResDTO.UserLogin userLogin = userCommandService.login(dto);
        UserSuccessCode code = UserSuccessCode.LOGIN;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), userLogin);
    }

    @GetMapping("/info")
    public ApiResponse<UserResDTO.UserInfo> getUserInfo(
            @CurrentUser User user
    ){
        UserResDTO.UserInfo userInfo = userQueryService.getUserInfo(user);
        SuccessStatus code = SuccessStatus.OK;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), userInfo);
    }
}
