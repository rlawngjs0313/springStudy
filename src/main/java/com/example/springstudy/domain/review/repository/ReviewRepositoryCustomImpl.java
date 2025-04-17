package com.example.springstudy.domain.review.repository;

import com.example.springstudy.domain.review.entity.Review;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {

    private final ReviewRepository reviewRepository;


    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
}
