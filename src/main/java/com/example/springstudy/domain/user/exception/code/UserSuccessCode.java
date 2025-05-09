package com.example.springstudy.domain.user.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseCode {
    LOGIN(HttpStatus.OK, "USER200", "정상적으로 로그인했습니다."),
    SIGN_UP(HttpStatus.CREATED, "USER201", "회원가입을 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
