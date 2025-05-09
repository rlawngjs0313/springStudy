package com.example.springstudy.domain.shop.service.command;

import com.example.springstudy.domain.shop.converter.ShopConverter;
import com.example.springstudy.domain.shop.dto.request.ShopReqDTO;
import com.example.springstudy.domain.shop.entity.Region;
import com.example.springstudy.domain.shop.exception.RegionException;
import com.example.springstudy.domain.shop.exception.code.RegionErrorCode;
import com.example.springstudy.domain.shop.repository.RegionRepository;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ShopCommandServiceImpl implements ShopCommandService {

    private final RegionRepository regionRepository;
    private final ShopRepository shopRepository;

    @Override
    public void setShop(Long regionId, ShopReqDTO.SetShop dto) {

        Region region = regionRepository.findRegionById(regionId).orElseThrow(()->
                new RegionException(RegionErrorCode.NOT_FOUND));

        shopRepository.save(ShopConverter.toShop(region, dto));
    }
}
