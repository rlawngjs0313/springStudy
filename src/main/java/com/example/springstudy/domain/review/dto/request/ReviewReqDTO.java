package com.example.springstudy.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ReviewReqDTO {

    public record SetReview(
            @NotNull(message = "별점은 필수로 작성하셔야 합니다.")
            @Max(value = 10, message = "별점은 10점 만점입니다.")
            @Min(value = 0, message = "별점은 0점 이상입니다.")
            Long star,
            String content
    ){}
}
