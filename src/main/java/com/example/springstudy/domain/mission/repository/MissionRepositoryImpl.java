package com.example.springstudy.domain.mission.repository;

import com.example.springstudy.domain.mission.converter.MissionConverter;
import com.example.springstudy.domain.mission.dto.MissionResDTO;
import com.example.springstudy.domain.mission.entity.QMission;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.shop.entity.QShop;
import com.example.springstudy.domain.user.entity.QUser;
import com.example.springstudy.mapping.QUserMission;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public Slice<MissionResDTO.MissionDTO> findFirstByMissionCurrent(String missionCurrent, Long userId, Pageable pageable) {
        // 필요한 엔티티 선언
        QShop shop = QShop.shop;
        QUserMission userMission = QUserMission.userMission;

        // 서브 쿼리 먼저 꺼내기
        JPQLQuery<Long> userMissionSubQuery = JPAExpressions
                .select(userMission.mission.id)
                .from(userMission)
                .where(userMission.missionCurrent.eq(MissionCurrent.valueOf(missionCurrent))
                        .and(userMission.user.id.eq(userId))
                );
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
                .join(mission.shop, shop)
                .on(shop.shopId.eq(mission.shop.shopId))
                .where(
                        mission.id.in(userMissionSubQuery)
                )
                .orderBy(mission.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);
    }

    @Override
    public Slice<MissionResDTO.MissionDTO> findAllByMissionCurrentOrderByCursor(
            String missionCurrent,
            Long userId,
            Long cursor,
            Pageable pageable
    ) {
        // 필요한 엔티티 선언
        QShop shop = QShop.shop;
        QUserMission userMission = QUserMission.userMission;

        // 서브 쿼리 먼저 꺼내기
        JPQLQuery<Long> userMissionSubQuery = JPAExpressions
                .select(userMission.mission.id)
                .from(userMission)
                .where(userMission.missionCurrent.eq(MissionCurrent.valueOf(missionCurrent))
                        .and(userMission.user.id.eq(userId))
                );
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
                .join(mission.shop, shop)
                .on(shop.shopId.eq(mission.shop.shopId))
                .where(
                        mission.id.in(userMissionSubQuery),
                        mission.id.goe(cursor)
                )
                .orderBy(mission.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();
        //Slice Page 정보 생성
        boolean hasNext = false;
        if (content.size() > pageable.getPageSize()) {
            content.remove(pageable.getPageSize());
            hasNext = true;
        }
        return new SliceImpl<>(content, pageable, hasNext);
    }

    @Override
    public MissionResDTO.HomePageDTO findFirstByUserIdAndCity(Long userId, String city, Pageable pageable) {

        // 필요한 객체 조회
        QUserMission userMission = QUserMission.userMission;
        QShop shop = QShop.shop;
        QUser user = QUser.user;
        // 유저-미션 서브 쿼리 미리 빼놓기
        JPQLQuery<Long> userMissionSubQuery = JPAExpressions
                .select(userMission.id)
                .from(userMission)
                .where(
                        userMission.id.eq(userId),
                        userMission.missionCurrent.eq(MissionCurrent.BEFORE_PROGRESS)
                );
        // 커서 먼저 빼놓기
        StringTemplate cursorValue = Expressions.stringTemplate(
                "CONCAT(LPAD({0}, 10, '0'), LPAD({1}, 10, '0'))",
                mission.missionTime,
                mission.id
        );
        // 메인 쿼리
        List<MissionResDTO.HomePageMissionDTO> missionList = queryFactory
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
        // 마지막 페이지 기준 커서값 생성
        MissionResDTO.HomePageMissionDTO lastPage = missionList.get(missionList.size() - 1);
        String resCursor = String.valueOf(Expressions.stringTemplate(
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

    @Override
    public MissionResDTO.HomePageDTO findAllByUserIdAndCityByCursor(Long userId, String city, String cursor, Pageable pageable) {

        // 필요한 객체 조회
        QUserMission userMission = QUserMission.userMission;
        QShop shop = QShop.shop;
        QUser user = QUser.user;

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
        // 메인 쿼리
        List<MissionResDTO.HomePageMissionDTO> missionList = queryFactory
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
