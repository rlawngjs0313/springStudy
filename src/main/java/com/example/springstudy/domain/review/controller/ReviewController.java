package com.example.springstudy.domain.review.controller;

import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.domain.review.exception.code.ReviewSuccessCode;
import com.example.springstudy.domain.review.service.command.ReviewCommandService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import com.example.springstudy.global.validation.annotation.ExistShop;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/shops/{shopId}/reviews")
    public ApiResponse<Void> setReview(
            @ExistShop @PathVariable Long shopId,
            @Valid @RequestBody ReviewReqDTO.SetReview dto
    ) {
        reviewCommandService.setReview(shopId, dto);

        BaseCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), null);
    }
}
