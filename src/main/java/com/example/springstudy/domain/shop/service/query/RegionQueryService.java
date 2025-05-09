package com.example.springstudy.domain.shop.service.query;

import com.example.springstudy.domain.shop.entity.Region;

public interface RegionQueryService {
    Region findRegionByRegionName(String regionName);
}
