package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.dto.MissionResDTO;
import org.springframework.data.domain.Pageable;

public interface MissionRepositoryCustom {
    MissionResDTO.PageMissionDTO findAllByMissionCurrent(
            String missionCurrent,
            Long userId,
            Long cursor,
            Pageable pageable
    );
    MissionResDTO.HomePageDTO findAllByUserIdAndCity(Long userId, String city, String cursor, Pageable pageable);
}
