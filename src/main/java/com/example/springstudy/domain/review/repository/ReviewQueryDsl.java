package com.example.springstudy.domain.review.repository;

import com.example.springstudy.domain.review.dto.response.ReviewResDTO;
import com.querydsl.core.types.Predicate;

public interface ReviewQueryDsl {

    // 내가 작성한 리뷰 목록 조회
    ReviewResDTO.Pageable<ReviewResDTO.Review> getMyReviews(
            Predicate subQuery,
            int size
    );
}
