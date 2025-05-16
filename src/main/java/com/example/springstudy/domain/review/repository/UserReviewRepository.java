package com.example.springstudy.domain.review.repository;

import com.example.springstudy.global.mapping.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
