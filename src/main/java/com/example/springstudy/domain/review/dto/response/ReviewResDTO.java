package com.example.springstudy.domain.review.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    // 리뷰
    @Builder
    public record Review(
            Long reviewId,
            String nickname,
            String reviewContent,
            LocalDateTime createdAt,
            Long star
    ){}

    // 페이지네이션
    @Builder
    public record Pageable<T>(
            List<T> reviews,
            boolean hasNext,
            Long cursor,
            int size
    ){}
}
