package com.example.springstudy.domain.shop.dto.request;

import jakarta.validation.constraints.NotNull;

public class RegionReqDTO {

    public record SetRegion(
            @NotNull(message = "지역명은 필수로 작성해야 합니다.")
            String regionName
    ){}

}
