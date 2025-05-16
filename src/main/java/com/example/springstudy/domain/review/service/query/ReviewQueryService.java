package com.example.springstudy.domain.review.service.query;

import com.example.springstudy.domain.review.dto.response.ReviewResDTO;
import com.example.springstudy.global.auth.CustomUserDetails;

public interface ReviewQueryService {
    // 내가 작성한 리뷰 조회
    ReviewResDTO.Pageable<ReviewResDTO.Review> getMyReviews(
            Long memberId,
            CustomUserDetails user,
            Long cursor,
            int size
    );
}
