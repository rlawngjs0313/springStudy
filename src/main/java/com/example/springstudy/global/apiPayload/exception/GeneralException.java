package com.example.springstudy.global.apiPayload.exception;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    private final BaseErrorCode code;

    public GeneralException(BaseErrorCode code){ this.code = code; }
}
