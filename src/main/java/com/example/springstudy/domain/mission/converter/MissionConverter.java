package com.example.springstudy.domain.mission.converter;

import com.example.springstudy.domain.mission.dto.MissionResDTO;

import java.util.List;

public class MissionConverter {

    // List<MissionDTO> -> PageMissionDTO
    public static MissionResDTO.PageMissionDTO toPageMissionDTO(
            List<MissionResDTO.MissionDTO> MissionList,
            Long cursor,
            int size
    ) {
        return MissionResDTO.PageMissionDTO.builder()
                .missionDTOList(MissionList)
                .cursor(cursor)
                .size(size)
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