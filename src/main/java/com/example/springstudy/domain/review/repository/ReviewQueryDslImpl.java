package com.example.springstudy.domain.review.repository;

import com.example.springstudy.domain.review.dto.response.ReviewResDTO;
import com.example.springstudy.domain.review.entity.QReview;
import com.example.springstudy.domain.review.exception.ReviewException;
import com.example.springstudy.domain.review.exception.code.ReviewErrorCode;
import com.example.springstudy.global.mapping.QUserReview;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    // 내가 작성한 리뷰 목록 조회
    @Override
    public ReviewResDTO.Pageable<ReviewResDTO.Review> getMyReviews(
            Predicate subQuery,
            int size
    ){
        // 조회할 객체 선언
        QReview review = QReview.review;
        QUserReview userReview = QUserReview.userReview;

        List<ReviewResDTO.Review> reviews = jpaQueryFactory
                .from(review)
                .leftJoin(userReview).on(userReview.review.id.eq(review.id))
                .where(subQuery)
                .orderBy(review.id.desc())
                .limit(size+1)
                .transform(GroupBy.groupBy(review.id).list(
                        Projections.constructor(
                                ReviewResDTO.Review.class,
                                review.id,
                                userReview.user.nickname,
                                review.reviewContent,
                                review.createdAt,
                                review.star
                        )
                ));

        return toPageable(reviews, size);
    }

    // 조회 리스트 받아서 페이지네이션
    private ReviewResDTO.Pageable<ReviewResDTO.Review> toPageable(
            List<ReviewResDTO.Review> reviews,
            int size
    ){
        // 만약 조회 결과가 없는 경우
        if (reviews.isEmpty()){
            throw new ReviewException(ReviewErrorCode.NOT_FOUND);
        }

        // 커서 설정
        boolean hasNext = reviews.size() > size;
        int pageSize = Math.min(reviews.size(), size);
        Long cursor = reviews.get(reviews.size()-1).reviewId();

        // 조회 결과 자르기
        reviews = reviews.subList(0, pageSize);
        return ReviewResDTO.Pageable.<ReviewResDTO.Review>builder()
                .reviews(reviews)
                .hasNext(hasNext)
                .cursor(cursor)
                .size(pageSize)
                .build();
    }
}
