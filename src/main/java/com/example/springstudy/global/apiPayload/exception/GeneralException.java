package com.example.springstudy.global.apiPayload.exception;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.dto.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;
    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
