package com.example.springstudy.domain.shop.converter;

import com.example.springstudy.domain.shop.dto.request.ShopReqDTO;
import com.example.springstudy.domain.shop.entity.Region;
import com.example.springstudy.domain.shop.entity.Shop;

public class ShopConverter {

    // setShopDTO -> Shop
    public static Shop toShop(Region region, ShopReqDTO.SetShop dto) {
        return Shop.builder()
                .shopName(dto.shopName())
                .managerId(dto.managerId())
                .region(region)
                .build();
    }
}
