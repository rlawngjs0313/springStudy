package com.example.springstudy.domain.mission.converter;

import com.example.springstudy.domain.mission.dto.MissionResDTO;
import com.example.springstudy.domain.mission.entity.Mission;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionConverter {

    // List<MissionDTO> -> PageMissionDTO
    public static MissionResDTO.PageMissionDTO toPageMissionDTO(List<MissionResDTO.MissionDTO> MissionList) {
        return MissionResDTO.PageMissionDTO.builder()
                .missionDTOList(MissionList)
                .build();
    }
}
