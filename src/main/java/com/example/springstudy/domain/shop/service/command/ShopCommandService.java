package com.example.springstudy.domain.shop.service.command;

import com.example.springstudy.domain.shop.dto.request.ShopReqDTO;

public interface ShopCommandService {

    void setShop(Long regionId, ShopReqDTO.SetShop dto);
}
