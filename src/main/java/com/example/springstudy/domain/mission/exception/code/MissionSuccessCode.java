package com.example.springstudy.domain.mission.exception.code;

import com.example.springstudy.global.apiPayload.code.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseCode {

    CREATED(HttpStatus.CREATED,
            "MISSION201",
            "미션 정보를 성공적으로 생성했습니다."),
    IN_PROGRESS(HttpStatus.OK,
            "MISSION200",
            "미션 도전 처리를 성공적으로 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
