package com.example.springstudy.domain.user.controller;

import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.exception.code.UserSuccessCode;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.auth.service.command.OAuthCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth2/callback")
public class OAuthController {

    private final OAuthCommandService oAuthCommandService;

    @GetMapping("/kakao")
    public ApiResponse<UserResDTO.UserLogin> kakaoCallback(
            @RequestParam(value = "code") String code
    ) {
        UserSuccessCode successCode = UserSuccessCode.LOGIN;
        return ApiResponse.onSuccess(
                successCode.getCode(),
                successCode.getMessage(),
                oAuthCommandService.login(code)
        );
    }
}
