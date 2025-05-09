package com.example.springstudy.domain.shop.service.command;

import com.example.springstudy.domain.shop.converter.RegionConverter;
import com.example.springstudy.domain.shop.converter.ShopConverter;
import com.example.springstudy.domain.shop.dto.request.RegionReqDTO;
import com.example.springstudy.domain.shop.entity.Region;
import com.example.springstudy.domain.shop.exception.RegionException;
import com.example.springstudy.domain.shop.exception.code.RegionErrorCode;
import com.example.springstudy.domain.shop.repository.RegionRepository;
import com.example.springstudy.domain.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionCommandServiceImpl implements RegionCommandService {

    private final RegionRepository regionRepository;
    private final ShopRepository shopRepository;

    @Override
    public void setRegion(RegionReqDTO.SetRegion dto) {

        Region region = RegionConverter.toRegion(dto.regionName());
        regionRepository.save(region);
    }

}
