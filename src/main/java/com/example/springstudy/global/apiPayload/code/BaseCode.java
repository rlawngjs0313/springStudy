package com.example.springstudy.global.apiPayload.code;

import com.example.springstudy.global.apiPayload.dto.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}
