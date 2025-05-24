package com.example.springstudy.global.auth.exception;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.exception.GeneralException;

public class OAuthException extends GeneralException {
    public OAuthException(BaseErrorCode code) {
        super(code);
    }
}
