package com.example.springstudy.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionDTO(int missionScore, int missionRq, String shopName, Long missionId) {}

    @Builder
    public record PageMissionDTO(List<MissionDTO> missionDTOList, int cursor, int size) {}
}
