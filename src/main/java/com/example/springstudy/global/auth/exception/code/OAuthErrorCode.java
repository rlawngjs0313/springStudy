package com.example.springstudy.global.auth.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OAuthErrorCode implements BaseErrorCode {

    OAUTH_TOKEN_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,
            "OAUTH500",
            "토큰을 발급받을 수 없습니다."),
    OAUTH_USER_INFO_FAIL(HttpStatus.INTERNAL_SERVER_ERROR,
            "OAUTH400_0",
            "사용자 정보를 가져오는데 실패했습니다."),
    OAUTH_PROVIDER_NOT_FOUND(HttpStatus.BAD_REQUEST,
            "OAUTH400_1",
            "지원하지 않는 인증 제공자입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
