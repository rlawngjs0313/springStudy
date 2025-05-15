package com.example.springstudy.domain.shop.exception;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.exception.GeneralException;

public class ShopException extends GeneralException {
    public ShopException(BaseErrorCode code) {
        super(code);
    }
}
