package com.example.springstudy.domain.review.service.command;

import com.example.springstudy.domain.review.converter.ReviewConverter;
import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.domain.review.entity.Review;
import com.example.springstudy.domain.review.repository.ReviewRepository;
import com.example.springstudy.domain.review.repository.UserReviewRepository;
import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.domain.shop.exception.ShopException;
import com.example.springstudy.domain.shop.exception.code.ShopErrorCode;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.exception.UserException;
import com.example.springstudy.domain.user.exception.code.UserErrorCode;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.CustomUserDetails;
import com.example.springstudy.global.mapping.UserReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final UserReviewRepository userReviewRepository;

    @Override
    public void setReview(
            Long shopId,
            ReviewReqDTO.SetReview dto,
            CustomUserDetails user
    ) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(()->
                new ShopException(ShopErrorCode.NOT_FOUND));
        User user1 = userRepository.findUserByEmail(user.getUsername()).orElseThrow(()->
                new UserException(UserErrorCode.NOT_FOUND));
        Review review = ReviewConverter.toReview(shop, dto);
        UserReview userReview = ReviewConverter.toUserReview(review, user1);
        reviewRepository.save(review);
        userReviewRepository.save(userReview);
    }
}
