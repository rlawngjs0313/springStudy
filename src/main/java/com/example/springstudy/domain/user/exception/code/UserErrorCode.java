package com.example.springstudy.domain.user.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "USER404", "유저를 찾지 못했습니다."),
    WRONG_ID_OR_PWD(HttpStatus.BAD_REQUEST, "USER400_0", "아이디 혹은 비밀번호가 틀렸습니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "USER403", "해당 유저의 접근 권한이 없습니다."),
    UNAUTH(HttpStatus.UNAUTHORIZED, "USER401", "로그인이 필요합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
