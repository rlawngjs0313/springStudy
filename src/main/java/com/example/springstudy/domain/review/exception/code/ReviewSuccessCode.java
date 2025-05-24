package com.example.springstudy.domain.review.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseCode {

    CREATED(HttpStatus.CREATED,
            "REVIEW201",
            "리뷰 정보를 성공적으로 생성했습니다."),
    OK(HttpStatus.OK,
            "REVIEW200",
            "리뷰 정보를 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
