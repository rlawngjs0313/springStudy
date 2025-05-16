package com.example.springstudy.domain.review.service.query;


import com.example.springstudy.domain.review.dto.response.ReviewResDTO;
import com.example.springstudy.domain.review.entity.QReview;
import com.example.springstudy.domain.review.repository.ReviewRepository;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.CustomUserDetails;
import com.example.springstudy.global.mapping.QUserReview;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewQueryServiceImpl implements ReviewQueryService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    // 내가 작성한 리뷰 조회
    @Override
    public ReviewResDTO.Pageable<ReviewResDTO.Review> getMyReviews(
            Long memberId,
            CustomUserDetails user,
            Long cursor,
            int size
    ) {

        // 해당 유저랑 memberId가 맞는가
        validateUser(user, memberId);

        // 조회할 객체 선언
        QUserReview userReview = QUserReview.userReview;
        QReview review = QReview.review;
        BooleanBuilder builder = new BooleanBuilder();

        // 조건 설정
        builder.and(userReview.user.id.eq(memberId));

        if (cursor != 0){
            builder.and(review.id.loe(cursor));
        }

        return reviewRepository.getMyReviews(builder, size);

    }

    private void validateUser(
            CustomUserDetails user,
            Long memberId
    ){
        String username = userRepository.findById(memberId).orElseThrow(() ->
                new UserException(UserErrorCode.NOT_FOUND))
                .getEmail();
        if (!user.getUsername().equals(username)){
            throw new UserException(UserErrorCode.FORBIDDEN);
        }
    }
}
