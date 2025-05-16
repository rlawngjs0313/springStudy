package com.example.springstudy.domain.mission.dto.response;

import com.example.springstudy.domain.mission.enums.MissionCurrent;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionDTO(
            Long missionScore,
            Long missionRq,
            String shopName,
            Long missionId
    ) {}

    @Builder
    public record MissionInprogress(
            Enum<MissionCurrent> missionCurrent,
            Long missionScore,
            Long missionRq,
            String shopName,
            Long missionId,
            LocalDateTime createdAt
    ){}

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
    public record PageMissionDTO<MissionDTO>(
            List<MissionDTO> missionDTOList,
            boolean hasNext,
            Long cursor,
            int size
    ) {}

    @Builder
    public record HomePageDTO(
            int userPoint,
            int missionCnt,
            List<HomePageMissionDTO> missionDTOList,
            String cursor,
            int size
    ) {}
}