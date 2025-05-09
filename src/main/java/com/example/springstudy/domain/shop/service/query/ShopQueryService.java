package com.example.springstudy.domain.shop.service.query;

import com.example.springstudy.domain.shop.entity.Shop;

import java.util.Optional;

public interface ShopQueryService {

    Optional<Shop> findShopByShopName(String shopName);
}
