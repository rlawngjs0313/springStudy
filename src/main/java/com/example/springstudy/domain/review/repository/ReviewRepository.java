package com.example.springstudy.domain.review.repository;

import com.example.springstudy.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryDsl {
}
