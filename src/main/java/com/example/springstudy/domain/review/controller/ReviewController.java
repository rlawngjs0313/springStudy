package com.example.springstudy.domain.review.controller;

import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.domain.review.dto.response.ReviewResDTO;
import com.example.springstudy.domain.review.exception.code.ReviewSuccessCode;
import com.example.springstudy.domain.review.service.command.ReviewCommandService;
import com.example.springstudy.domain.review.service.query.ReviewQueryService;
import com.example.springstudy.global.apiPayload.ApiResponse;
import com.example.springstudy.global.apiPayload.code.BaseCode;
import com.example.springstudy.global.auth.CustomUserDetails;
import com.example.springstudy.global.auth.annotation.CurrentUser;
import com.example.springstudy.global.validation.annotation.ExistShop;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class ReviewController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // 리뷰 작성
    @PostMapping("/shops/{shopId}/reviews")
    public ApiResponse<Void> setReview(
            @ExistShop @PathVariable Long shopId,
            @CurrentUser CustomUserDetails user,
            @Valid @RequestBody ReviewReqDTO.SetReview dto
    ) {
        reviewCommandService.setReview(shopId, dto, user);

        BaseCode code = ReviewSuccessCode.CREATED;
        return ApiResponse.onSuccess(code.getCode(), code.getMessage(), null);
    }

    // 내가 작성한 리뷰 목록
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<ReviewResDTO.Pageable<ReviewResDTO.Review>> getMyReviews(
            @PathVariable @NotNull(message = "memberId는 필수값입니다.")
            Long memberId,
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "cursor는 0보다 큰 값이어야 합니다.")
            Long cursor,
            @RequestParam(defaultValue = "1") @Min(value = 1, message = "한 개 이상 조회해야 합니다.")
            int size
    ){
        ReviewSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(
                code.getCode(),
                code.getMessage(),
                reviewQueryService.getMyReviews(
                        memberId,
                        user,
                        cursor,
                        size
                )
        );
    }
}
