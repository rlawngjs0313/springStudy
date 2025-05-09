package com.example.springstudy.domain.shop.converter;

import com.example.springstudy.domain.shop.dto.response.RegionResDTO;
import com.example.springstudy.domain.shop.entity.Region;

public class RegionConverter {

    // regionName -> region
    public static Region toRegion(String regionName) {
        return Region.builder()
                .regionName(regionName)
                .build();
    }

    // region -> regionIdDTO
    public static RegionResDTO.RegionId toRegionId(Region region) {
        return RegionResDTO.RegionId.builder()
                .id(region.getId())
                .build();
    }
}
