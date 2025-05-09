package com.example.springstudy.domain.review.converter;

import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.domain.review.entity.Review;
import com.example.springstudy.domain.shop.entity.Shop;

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
}
