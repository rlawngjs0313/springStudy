package com.example.springstudy.global.apiPayload.code;

import com.example.springstudy.global.apiPayload.dto.ErrorReasonDTO;

public interface BaseErrorCode {

    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();
}
