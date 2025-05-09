package com.example.springstudy.domain.mission.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404",
            "미션을 찾지 못했습니다."),
    NOT_BEFORE_PROGRESS(HttpStatus.BAD_REQUEST,
            "MISSION400_0",
            "미션 도전을 할 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
