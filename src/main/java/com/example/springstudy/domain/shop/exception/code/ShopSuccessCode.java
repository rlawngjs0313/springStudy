package com.example.springstudy.domain.shop.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ShopSuccessCode implements BaseCode {

    CREATED(HttpStatus.CREATED,
            "SHOP201",
            "가게 정보를 성공적으로 생성했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
