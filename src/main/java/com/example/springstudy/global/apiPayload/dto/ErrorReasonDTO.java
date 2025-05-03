package com.example.springstudy.global.apiPayload.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorReasonDTO {

    private HttpStatus status;

    private final boolean isSuccess;
    private final String code;
    private final String message;
}
