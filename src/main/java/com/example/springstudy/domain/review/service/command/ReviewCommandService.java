package com.example.springstudy.domain.review.service.command;

import com.example.springstudy.domain.review.dto.request.ReviewReqDTO;
import com.example.springstudy.global.auth.CustomUserDetails;

public interface ReviewCommandService {

    void setReview(Long shopId, ReviewReqDTO.SetReview dto, CustomUserDetails user);
}
