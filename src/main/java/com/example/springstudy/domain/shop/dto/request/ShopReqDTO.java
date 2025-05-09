package com.example.springstudy.domain.shop.dto.request;

import jakarta.validation.constraints.NotNull;

public class ShopReqDTO {

    public record SetShop(
            @NotNull(message = "상점 이름은 필수로 작성해야 합니다.")
            String shopName,
            @NotNull(message = "매니저 ID는 필수로 작성해야 합니다.")
            Long managerId
    ){}
}
