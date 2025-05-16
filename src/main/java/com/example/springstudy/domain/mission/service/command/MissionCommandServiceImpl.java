package com.example.springstudy.domain.mission.service.command;

import com.example.springstudy.domain.mission.converter.MissionConverter;
import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;
import com.example.springstudy.domain.mission.entity.Mission;
import com.example.springstudy.domain.mission.enums.MissionCurrent;
import com.example.springstudy.domain.mission.exception.MissionException;
import com.example.springstudy.domain.mission.exception.code.MissionErrorCode;
import com.example.springstudy.domain.mission.repository.MissionRepository;
import com.example.springstudy.domain.mission.repository.UserMissionRepository;
import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.domain.shop.exception.ShopException;
import com.example.springstudy.domain.shop.exception.code.ShopErrorCode;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.domain.user.service.query.UserQueryService;
import com.example.springstudy.global.auth.CustomUserDetails;
import com.example.springstudy.global.mapping.UserMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final ShopRepository shopRepository;
    private final UserQueryService userQueryService;
    private final UserRepository userRepository;

    @Override
    public void setMission(Long shopId, MissionReqDTO.Mission dto) {

        // 미션 저장
        Shop shop = shopRepository.findById(shopId).orElseThrow(()->
                new ShopException(ShopErrorCode.NOT_FOUND));
        Mission mission = MissionConverter.toMission(shop, dto);
        missionRepository.save(mission);

        // 미션, 유저 연동
        linkingUserMission(mission);
    }

    @Override
    public void acceptMission(Long shopId, Long missionId) {

        shopRepository.findById(shopId).orElseThrow(()->
                new ShopException(ShopErrorCode.NOT_FOUND));
        missionRepository.findById(missionId).orElseThrow(()->
                new MissionException(MissionErrorCode.NOT_FOUND));

        Long userId = userQueryService.getAuthUserId().getId();
        MissionReqDTO.ValidMissionCurrent dto = MissionConverter.toValidDTO(userId, missionId);
        missionRepository.updateMissionCurrent(dto);
    }

    // 미션 진행 완료 처리
    @Override
    public void completeMission(
            CustomUserDetails user,
            Long missionId
    ) {
        missionRepository.findById(missionId).orElseThrow(()->
                new MissionException(MissionErrorCode.NOT_FOUND));
        Long userId = userRepository.findByEmail(user.getUsername()).orElseThrow(() ->
                new UserException(UserErrorCode.NOT_FOUND))
                .getId();

        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(()-> new MissionException(MissionErrorCode.NOT_FOUND));

        userMission.updateMissionCurrent(MissionCurrent.AFTER_PROGRESS);
    }

    private void linkingUserMission(Mission mission){

        // 유저 가져오기
        User user = userQueryService.getAuthUserId();

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .missionCurrent(MissionCurrent.BEFORE_PROGRESS)
                .build();

        // 유저 미션 연동
        userMissionRepository.save(userMission);
    }
}
