package com.example.springstudy.domain.mission.converter;

import com.example.springstudy.domain.mission.dto.MissionResDTO;
import com.querydsl.core.types.dsl.NumberPath;

import java.util.List;

public class MissionConverter {

    // List<MissionDTO> -> PageMissionDTO
    public static MissionResDTO.PageMissionDTO toPageMissionDTO(List<MissionResDTO.MissionDTO> MissionList) {
        return MissionResDTO.PageMissionDTO.builder()
                .missionDTOList(MissionList)
                .build();
    }

    // HomePageInfo + List<MissionDTO> + PageInfo -> HomePageDTO
    public static MissionResDTO.HomePageDTO toHomePageDTO(
            List<MissionResDTO.HomePageMissionDTO> HomePageList,
            int userPoint,
            int missionCnt,
            String cursor,
            int size
    ) {
        return MissionResDTO.HomePageDTO.builder()
                .missionDTOList(HomePageList)
                .missionCnt(missionCnt)
                .cursor(cursor)
                .userPoint(userPoint)
                .size(size)
                .build();
    }
}
