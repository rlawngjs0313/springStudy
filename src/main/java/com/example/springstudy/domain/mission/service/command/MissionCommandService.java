package com.example.springstudy.domain.mission.service.command;

import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;
import com.example.springstudy.global.auth.CustomUserDetails;

public interface MissionCommandService {

    void setMission(Long shopId, MissionReqDTO.Mission dto);

    void acceptMission(Long shopId, Long missionId);

    // 미션 진행 완료 처리
    void completeMission(
            CustomUserDetails user,
            Long missionId
    );
}
