package com.example.springstudy.domain.mission.controller;

import com.example.springstudy.domain.mission.dto.request.MissionReqDTO;
import com.example.springstudy.domain.mission.exception.code.MissionSuccessCode;
import com.example.springstudy.domain.mission.service.command.MissionCommandService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import com.example.springstudy.global.validation.annotation.ExistShop;
import jakarta.validation.Valid;
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
}
