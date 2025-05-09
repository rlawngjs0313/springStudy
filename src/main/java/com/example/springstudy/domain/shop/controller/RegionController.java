package com.example.springstudy.domain.shop.controller;

import com.example.springstudy.domain.shop.converter.RegionConverter;
import com.example.springstudy.domain.shop.dto.request.RegionReqDTO;
import com.example.springstudy.domain.shop.dto.response.RegionResDTO;
import com.example.springstudy.domain.shop.entity.Region;
import com.example.springstudy.domain.shop.service.command.RegionCommandServiceImpl;
import com.example.springstudy.domain.shop.service.query.RegionQueryService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import com.example.springstudy.global.apiPayload.code.SuccessStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegionController {

    private final RegionCommandServiceImpl regionCommandService;
    private final RegionQueryService regionQueryService;

    @PostMapping("/regions")
    public ApiResponse<Void> setRegion(
            @Valid @RequestBody RegionReqDTO.SetRegion dto
    ){
        regionCommandService.setRegion(dto);
        return ApiResponse.created();
    }

    @GetMapping("/regions")
    public ApiResponse<RegionResDTO.RegionId> findRegion(
            @RequestParam String regionName
    ) {
        Region region = regionQueryService.findRegionByRegionName(regionName);

        BaseCode code = SuccessStatus.OK;
        RegionResDTO.RegionId regionId = RegionConverter.toRegionId(region);
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), regionId);
    }
}
