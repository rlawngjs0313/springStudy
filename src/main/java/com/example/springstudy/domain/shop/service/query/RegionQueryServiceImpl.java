package com.example.springstudy.domain.shop.service.query;

import com.example.springstudy.domain.shop.entity.Region;
import com.example.springstudy.domain.shop.exception.RegionException;
import com.example.springstudy.domain.shop.exception.code.RegionErrorCode;
import com.example.springstudy.domain.shop.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionQueryServiceImpl implements RegionQueryService {

    private final RegionRepository regionRepository;

    @Override
    public Region findRegionByRegionName(String regionName) {

        return regionRepository.findRegionByRegionName(regionName).orElseThrow(()->
                new RegionException(RegionErrorCode.NOT_FOUND));
    }
}
