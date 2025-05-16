package com.example.springstudy.domain.review.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404",
            "리뷰를 찾지 못했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
