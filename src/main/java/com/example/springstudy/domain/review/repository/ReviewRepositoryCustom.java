package com.example.springstudy.domain.review.repository;

import com.example.springstudy.domain.review.entity.Review;

public interface ReviewRepositoryCustom {
    Review saveReview(Review review);
}
