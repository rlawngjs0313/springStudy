package com.example.springstudy.domain.mission.exception;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import com.example.springstudy.global.apiPayload.exception.GeneralException;

public class MissionException extends GeneralException {
    public MissionException(BaseErrorCode code) {
        super(code);
    }
}
