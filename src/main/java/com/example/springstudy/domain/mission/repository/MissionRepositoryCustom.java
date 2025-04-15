package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.dto.MissionResDTO;
import com.example.springstudy.domain.mission.entity.Mission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MissionRepositoryCustom {
    MissionResDTO.PageMissionDTO findAllByMissionCurrent(String missionCurrent, Long userId, Long cursor, Pageable pageable);
}
