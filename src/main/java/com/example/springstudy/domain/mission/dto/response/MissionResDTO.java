package com.example.springstudy.domain.mission.dto.response;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionDTO(int missionScore, int missionRq, String shopName, Long missionId) {}

    @Builder
    public record HomePageMissionDTO(
            int missionId,
            int missionScore,
            int missionTime,
            int missionRq,
            String shopName,
            String cursor,
            int missionCnt
    ) {}

    @Builder
    public record PageMissionDTO(List<MissionDTO> missionDTOList, Long cursor, int size) {}

    @Builder
    public record HomePageDTO(
            int userPoint,
            int missionCnt,
            List<HomePageMissionDTO> missionDTOList,
            String cursor,
            int size
    ) {}
}