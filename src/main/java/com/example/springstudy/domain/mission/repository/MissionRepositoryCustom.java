package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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
}