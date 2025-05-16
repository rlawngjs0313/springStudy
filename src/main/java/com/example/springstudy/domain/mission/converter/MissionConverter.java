package com.example.springstudy.domain.mission.converter;

import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;
import com.example.springstudy.domain.mission.dto.response.MissionResDTO;
import com.example.springstudy.domain.mission.entity.Mission;
import com.example.springstudy.domain.shop.entity.Shop;

import java.util.List;

public class MissionConverter {

    // 검증용) userId, missionId -> ValidDTO
    public static MissionReqDTO.ValidMissionCurrent toValidDTO(
            Long userId,
            Long missionId
    ){
        return MissionReqDTO.ValidMissionCurrent.builder()
                .userId(userId)
                .missionId(missionId)
                .build();
    }

    // shopId, MissionContent -> Mission
    public static Mission toMission(
            Shop shop,
            MissionReqDTO.Mission dto
    ){
      return Mission.builder()
              .missionScore(dto.missionScore())
              .missionTime(dto.missionTime())
              .missionReq(dto.missionReq())
              .shop(shop)
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

    // 미션 목록 조회
    public static MissionResDTO.PageMissionDTO<MissionResDTO.MissionDTO> toPageMissionDTO(
            List<MissionResDTO.MissionDTO> missionList,
            boolean hasNext,
            Long cursor,
            int size
    ){
        return MissionResDTO.PageMissionDTO.<MissionResDTO.MissionDTO>builder()
                .missionDTOList(missionList)
                .hasNext(hasNext)
                .cursor(cursor)
                .size(size)
                .build();
    }

    // 미션 진행 상황 페이지네이션
    public static MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress> toPageMissionInprogress(
            List<MissionResDTO.MissionInprogress> missionInprogressList,
            boolean hasNext,
            Long cursor,
            int size
    ){
        return MissionResDTO.PageMissionDTO.<MissionResDTO.MissionInprogress>builder()
                .missionDTOList(missionInprogressList)
                .hasNext(hasNext)
                .cursor(cursor)
                .size(size)
                .build();
    }
}