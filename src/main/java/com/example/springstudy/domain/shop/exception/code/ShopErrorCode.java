package com.example.springstudy.domain.shop.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ShopErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "SHOP404",
            "상점을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
