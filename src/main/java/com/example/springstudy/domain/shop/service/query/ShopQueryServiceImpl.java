package com.example.springstudy.domain.shop.service.query;

import com.example.springstudy.domain.shop.entity.Shop;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopQueryServiceImpl implements ShopQueryService {

    private final ShopRepository shopRepository;

    @Override
    public Optional<Shop> findShopByShopName(String shopName) {
        return Optional.empty();
    }
}
