package com.example.springstudy.domain.review.converter;

import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.domain.review.dto.response.ReviewResDTO;
import com.example.springstudy.domain.review.entity.Review;
import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.global.mapping.UserReview;

import java.util.List;

public class ReviewConverter {

    // Shop, star, content -> Review
    public static Review toReview(
            Shop shop,
            ReviewReqDTO.SetReview dto
    ){
        return Review.builder()
                .reviewContent(dto.content())
                .star(dto.star())
                .shop(shop)
                .build();
    }

    // 유저, 리뷰 -> UserReview
    public static UserReview toUserReview(
            Review review,
            User user
    ){
        return UserReview.builder()
                .user(user)
                .review(review)
                .build();
    }

    // 리뷰 페이지네이션
    public static <T>ReviewResDTO.Pageable<T> toPageable(
            List<T> reviews,
            boolean hasNext,
            Long cursor,
            int size
    ){
        return ReviewResDTO.Pageable.<T>builder()
                .reviews(reviews)
                .hasNext(hasNext)
                .cursor(cursor)
                .size(size)
                .build();
    }
}
