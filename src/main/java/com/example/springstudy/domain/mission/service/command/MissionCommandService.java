package com.example.springstudy.domain.mission.service.command;

import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;

public interface MissionCommandService {

    void setMission(Long shopId, MissionReqDTO.Mission dto);

    void acceptMission(Long shopId, Long missionId);
}
