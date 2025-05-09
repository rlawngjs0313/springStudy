package com.example.springstudy.domain.mission.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

public class MissionReqDTO {

    public record Mission(
            @NotNull(message = "미션 성공 점수는 필수로 입력해야 합니다.")
            Long missionScore,
            @NotNull(message = "미션 기간은 필수로 입력해야 합니다.")
            @Future(message = "미션 기간이 이미 지난 경우는 허용하지 않습니다.")
            LocalDateTime missionTime,
            @NotNull(message = "미션 성공 조건은 필수로 입력해야 합니다.")
            Long missionReq
    ) {}

    @Builder
    public record ValidMissionCurrent(
            Long userId,
            Long missionId
    ){}
}
