package com.example.springstudy.domain.mission.service.query;

import com.example.springstudy.domain.mission.dto.response.MissionResDTO;
import com.example.springstudy.domain.mission.entity.QMission;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.mission.repository.MissionRepository;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.CustomUserDetails;
import com.example.springstudy.global.mapping.QUserMission;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    // 특정 가게 미션 조회
    @Override
    public MissionResDTO.PageMissionDTO<MissionResDTO.MissionDTO> findMissionByShopId(
            Long shopId,
            Long cursor,
            int size
    ) {
        QMission mission = QMission.mission;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(mission.shop.shopId.eq(shopId));

        if (!cursor.equals(0L)){
            builder.and(mission.id.loe(cursor));
        }
        return missionRepository.findByShopId(builder, size);
    }

    // 내가 진행중인 미션 목록 조회
    @Override
    public MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress> findMyInProgressMissions(
            Long memberId,
            CustomUserDetails user,
            Long cursor,
            int size
    ) {

        validateUser(user, memberId);

        QMission mission = QMission.mission;
        QUserMission userMission = QUserMission.userMission;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(userMission.user.id.eq(memberId))
                .and(userMission.missionCurrent.eq(MissionCurrent.IN_PROGRESS));

        if (!cursor.equals(0L)){
            builder.and(mission.id.loe(cursor));
        }
        return missionRepository.findMyMissionInProgress(builder, size);
    }

    // 진행중인 미션 진행 완료
    @Override
    public MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress> findMyCompleteMissions(
            CustomUserDetails user,
            Long cursor,
            int size
    ) {

        Long memberId = userRepository.findByEmail(user.getUsername()).orElseThrow(() ->
                new UserException(UserErrorCode.NOT_FOUND))
                .getId();

        QMission mission = QMission.mission;
        QUserMission userMission = QUserMission.userMission;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(userMission.user.id.eq(memberId))
                .and(userMission.missionCurrent.eq(MissionCurrent.AFTER_PROGRESS));

        if (!cursor.equals(0L)){
            builder.and(mission.id.loe(cursor));
        }
        return missionRepository.findMyMissionInProgress(builder, size);
    }

    private void validateUser(CustomUserDetails user, Long memberId){
        String username = userRepository.findById(memberId).orElseThrow(() ->
                new UserException(UserErrorCode.NOT_FOUND))
                .getEmail();
        if (!user.getUsername().equals(username)){
            throw new UserException(UserErrorCode.FORBIDDEN);
        }
    }
}
