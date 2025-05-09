package com.example.springstudy.domain.shop.service.command;

import com.example.springstudy.domain.shop.dto.request.RegionReqDTO;

public interface RegionCommandService {

    void setRegion(RegionReqDTO.SetRegion dto);
}
