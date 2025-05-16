package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;
import com.example.springstudy.domain.mission.dto.response.MissionResDTO;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.global.validation.annotation.HasInProgress;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.validation.annotation.Validated;

@Validated
public interface MissionRepositoryCustom {
    Slice<MissionResDTO.MissionDTO> findFirstByMissionCurrent(
            String missionCurrent,
            Long userId,
            Pageable pageable
    );
    Slice<MissionResDTO.MissionDTO> findAllByMissionCurrentOrderByCursor(
            String missionCurrent,
            Long userId,
            Long cursor,
            Pageable pageable
    );

    MissionResDTO.HomePageDTO findAllByUserIdAndCity(Long userId, String city, String cursor, Pageable pageable);

    void updateMissionCurrent(@HasInProgress MissionReqDTO.ValidMissionCurrent dto);

    MissionCurrent findByUserIdAndMissionId(Long userId, Long missionId);

    // 특정 가게 미션 조회
    MissionResDTO.PageMissionDTO<MissionResDTO.MissionDTO> findByShopId(
            Predicate subQuery,
            int size
    );

    // 내가 진행중인 미션 목록 조회
    MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress> findMyMissionInProgress(
            Predicate subQuery,
            int size
    );
}