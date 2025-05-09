package com.example.springstudy.domain.review.service.command;

import com.example.springstudy.domain.review.converter.ReviewConverter;
import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.domain.review.entity.Review;
import com.example.springstudy.domain.review.repository.ReviewRepository;
import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.domain.shop.exception.ShopException;
import com.example.springstudy.domain.shop.exception.code.ShopErrorCode;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final ShopRepository shopRepository;

    @Override
    public void setReview(Long shopId, ReviewReqDTO.SetReview dto) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(()->
                new ShopException(ShopErrorCode.NOT_FOUND));
        Review review = ReviewConverter.toReview(shop, dto);
        reviewRepository.save(review);
    }
}
