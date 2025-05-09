package com.example.springstudy.domain.shop.service.command;

import com.example.springstudy.domain.shop.dto.request.RegionReqDTO;
import com.example.springstudy.domain.shop.entity.Region;

public interface RegionCommandService {

    void setRegion(RegionReqDTO.SetRegion dto);
}
