package com.example.springstudy.domain.mission.service.query;

import com.example.springstudy.domain.mission.dto.response.MissionResDTO;
import com.example.springstudy.global.auth.CustomUserDetails;

public interface MissionQueryService {
    // 특정 가게 미션 조회
    MissionResDTO.PageMissionDTO<MissionResDTO.MissionDTO> findMissionByShopId(
            Long shopId,
            Long cursor,
            int size
    );

    // 내가 진행중인 미션 목록 조회
    MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress> findMyInProgressMissions(
            Long memberId,
            CustomUserDetails user,
            Long cursor,
            int size
    );

    // 진행중인 미션 진행 완료
    MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress> findMyCompleteMissions(
            CustomUserDetails user,
            Long cursor,
            int size
    );
}
