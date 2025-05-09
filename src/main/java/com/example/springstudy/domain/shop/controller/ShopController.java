package com.example.springstudy.domain.shop.controller;

import com.example.springstudy.domain.shop.dto.request.ShopReqDTO;
import com.example.springstudy.domain.shop.exception.code.ShopSuccessCode;
import com.example.springstudy.domain.shop.service.command.ShopCommandService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Transactional
@Validated
public class ShopController {

    private final ShopCommandService shopCommandService;

    @PostMapping("/regions/{regionId}/shops")
    public ApiResponse<Void> setShop(
            @PathVariable Long regionId,
            @Valid @RequestBody ShopReqDTO.SetShop dto
    ) {
        shopCommandService.setShop(regionId, dto);
        BaseCode code = ShopSuccessCode.CREATED;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), null);
    }

}
