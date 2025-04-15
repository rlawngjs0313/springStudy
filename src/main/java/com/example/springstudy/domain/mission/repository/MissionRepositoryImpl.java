package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.converter.MissionConverter;
import com.example.springstudy.domain.mission.dto.MissionResDTO;
import com.example.springstudy.domain.mission.entity.Mission;
import com.example.springstudy.domain.mission.entity.QMission;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.shop.entity.QShop;
import com.example.springstudy.mapping.QUserMission;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QMission mission = QMission.mission;

    @Override
    public MissionResDTO.PageMissionDTO findAllByMissionCurrent(String missionCurrent, Long userId, Long cursor, Pageable pageable) {
        List<MissionResDTO.MissionDTO> content = jpaQueryFactory
                .select(
                        Projections.fields(MissionResDTO.MissionDTO.class,
                                mission.id,
                                mission.missionReq,
                                mission.missionScore,
                                mission.shop.shopName
                        )
                )
                .from(mission)
                .join(mission.shop, QShop.shop)
                .on(QShop.shop.shopId.eq(mission.shop.shopId))
                .where(
                        mission.id.in(
                                JPAExpressions
                                        .select(QUserMission.userMission.mission.id)
                                        .from(QUserMission.userMission)
                                        .where(QUserMission.userMission.missionCurrent.eq(MissionCurrent.valueOf(missionCurrent))
                                                .and(QUserMission.userMission.user.id.eq(userId))
                                        )
                        )
                                .and(mission.id.goe(cursor))
                )
                .orderBy(mission.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return MissionConverter.toPageMissionDTO(content);
    }
}
