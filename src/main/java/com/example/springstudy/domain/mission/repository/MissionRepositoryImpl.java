package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.converter.MissionConverter;
import com.example.springstudy.domain.mission.dto.MissionResDTO;
import com.example.springstudy.domain.mission.entity.Mission;
import com.example.springstudy.domain.mission.entity.QMission;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.shop.entity.QShop;
import com.example.springstudy.domain.user.entity.QUser;
import com.example.springstudy.domain.user.entity.QUserInfo;
import com.example.springstudy.mapping.QUserMission;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.eclipse.jdt.internal.compiler.ast.NullLiteral;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final QMission mission = QMission.mission;

    @Override
    public MissionResDTO.PageMissionDTO findAllByMissionCurrent(String missionCurrent, Long userId, Long cursor, Pageable pageable) {


        List<MissionResDTO.MissionDTO> content = queryFactory
                .select(
                        Projections.fields(MissionResDTO.MissionDTO.class,
                                mission.id,
                                mission.missionTime,
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

    @Override
    public MissionResDTO.HomePageDTO findAllByUserIdAndCity(Long userId, String city, String cursor, Pageable pageable) {

        // 필요한 객체 조회
        QUserMission userMission = QUserMission.userMission;
        QShop shop = QShop.shop;
        QUser user = QUser.user;

        List<MissionResDTO.HomePageMissionDTO> missionList;
        String resCursor;
        // 유저-미션 서브 쿼리 미리 빼놓기
        JPQLQuery<Long> userMissionSubQuery = JPAExpressions
                .select(userMission.id)
                .from(userMission)
                .where(
                        userMission.id.eq(userId),
                        userMission.missionCurrent.eq(MissionCurrent.BEFORE_PROGRESS)
                );
        // 커서값 미리 빼놓기
        StringTemplate cursorValue = Expressions.stringTemplate(
                "CONCAT(LPAD({0}, 10, '0'), LPAD({1}, 10, '0'))",
                mission.missionTime,
                mission.id
        );
        // 유저 포인트 조회
        Optional<Integer> userPoint = Optional.ofNullable(
                queryFactory
                        .select(user.userInfo.userPoint)
                        .from(user)
                        .where(user.id.eq(userId))
                        .fetchOne()
        );
        userPoint.orElseThrow(() ->
                new RuntimeException("Point not found"));
        // 처음 조회하는 경우
        if (cursor.isEmpty()){
            missionList = queryFactory
                    .select(
                            Projections.fields(
                                    MissionResDTO.HomePageMissionDTO.class,
                                    mission.id,
                                    mission.missionScore,
                                    mission.missionTime,
                                    mission.missionReq,
                                    mission.shop.shopName,
                                    cursorValue,
                                    mission.count().as("missionCnt")
                            )
                    )
                    .from(mission)
                    .rightJoin(shop).on(mission.shop.shopId.eq(shop.shopId))
                    .where(
                            mission.id.in(userMissionSubQuery),
                            mission.shop.shopCity.eq(city)
                    )
                    .orderBy(mission.id.asc(), mission.missionTime.asc())
                    .limit(pageable.getPageSize())
                    .fetch();
        } else {
            missionList = queryFactory
                    .select(
                            Projections.fields(
                                    MissionResDTO.HomePageMissionDTO.class,
                                    mission.id,
                                    mission.missionScore,
                                    mission.missionTime,
                                    mission.missionReq,
                                    mission.shop.shopName,
                                    cursorValue,
                                    mission.count().as("missionCnt")
                            )
                    )
                    .from(mission)
                    .rightJoin(shop).on(mission.shop.shopId.eq(shop.shopId))
                    .where(
                            mission.id.in(userMissionSubQuery),
                            mission.shop.shopCity.eq(city),
                            cursorValue.gt(cursor)
                    )
                    .orderBy(cursorValue.desc())
                    .limit(pageable.getPageSize())
                    .fetch();
        }
        // 마지막 페이지 기준 커서값 생성
        MissionResDTO.HomePageMissionDTO lastPage = missionList.get(missionList.size() - 1);
        resCursor = String.valueOf(Expressions.stringTemplate(
                "CONCAT(LPAD({0}, 10, '0'), LPAD({1}, 10, '0'))",
                lastPage.missionTime(),
                lastPage.missionId()
        ));
        return MissionConverter.toHomePageDTO(
                missionList,
                userPoint.get(),
                missionList.size(),
                resCursor,
                pageable.getPageSize()
        );
    }
}
