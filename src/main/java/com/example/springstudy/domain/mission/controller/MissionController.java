package com.example.springstudy.domain.mission.controller;

import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;
import com.example.springstudy.domain.mission.dto.response.MissionResDTO;
import com.example.springstudy.domain.mission.exception.code.MissionSuccessCode;
import com.example.springstudy.domain.mission.service.command.MissionCommandService;
import com.example.springstudy.domain.mission.service.query.MissionQueryService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import com.example.springstudy.global.auth.CustomUserDetails;
import com.example.springstudy.global.auth.annotation.CurrentUser;
import com.example.springstudy.global.validation.annotation.ExistShop;
import com.example.springstudy.global.validation.annotation.HasAfterProgress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Transactional
@Validated
public class MissionController {

    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @PostMapping("/shops/{shopId}/missions")
    public ApiResponse<Void> setMission(
            @ExistShop @PathVariable Long shopId,
            @Valid @RequestBody MissionReqDTO.Mission dto
    ) {

        // 미션 저장, 유저/미션 연동
        missionCommandService.setMission(shopId, dto);

        BaseCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), null);
    }

    @PostMapping("/shops/{shopId}/missions/{missionId}/accept")
    public ApiResponse<Void> acceptMission(
            @ExistShop @PathVariable Long shopId,
            @PathVariable Long missionId
    ){
        missionCommandService.acceptMission(shopId, missionId);
        BaseCode code = MissionSuccessCode.IN_PROGRESS;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), null);
    }

    // 특정 가게의 미션 조회
    @GetMapping("/shops/{shopId}/missions")
    public ApiResponse<MissionResDTO.PageMissionDTO<MissionResDTO.MissionDTO>> getMissions(
            @PathVariable @ExistShop Long shopId,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "cursor는 0보다 큰 값이어야 합니다.")
            Long cursor,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "한 개 이상 조회해야 합니다.")
            int size
    ){

        MissionSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                missionQueryService.findMissionByShopId(shopId, cursor, size)
        );
    }

    // 내가 진행중인 미션 목록 조회
    @GetMapping("members/{memberId}/missions/in-progress")
    public ApiResponse<MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress>> getMyInProgressMissions(
            @PathVariable Long memberId,
            @CurrentUser CustomUserDetails user,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "cursor는 0보다 큰 값이어야 합니다.")
            Long cursor,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "한 개 이상 조회해야 합니다.")
            int size
    ){
        MissionSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                missionQueryService.findMyInProgressMissions(
                        memberId,
                        user,
                        cursor,
                        size
                )
        );
    }

    // 진행중인 미션 진행 완료로 변경
    @PostMapping("/missions/{missionId}/complete")
    public ApiResponse<MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress>> completeMission(
            @PathVariable @HasAfterProgress Long missionId,
            @CurrentUser CustomUserDetails user,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "cursor는 0보다 큰 값이어야 합니다.")
            Long cursor,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "한 개 이상 조회해야 합니다.")
            int size
    ){

        missionCommandService.completeMission(user, missionId);

        MissionSuccessCode code = MissionSuccessCode.COMPLETE;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                missionQueryService.findMyCompleteMissions(
                        user,
                        cursor,
                        size
                )
        );
    }

    // 진행 완료한 미션 조회
    @GetMapping("members/{memberId}/missions/after-progress")
    public ApiResponse<MissionResDTO.PageMissionDTO<MissionResDTO.MissionInprogress>> getMyAfterProgressMissions(
            @PathVariable Long memberId,
            @CurrentUser CustomUserDetails user,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "cursor는 0보다 큰 값이어야 합니다.")
            Long cursor,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "한 개 이상 조회해야 합니다.")
            int size
    ){
        MissionSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                missionQueryService.findMyCompleteMissions(
                        user,
                        cursor,
                        size
                )
        );
    }
}
