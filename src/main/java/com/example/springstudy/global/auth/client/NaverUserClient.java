package com.example.springstudy.global.auth.client;

import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "naverUserClient",
        url = "https://openapi.naver.com",
        configuration = FeignConfig.class
)
public interface NaverUserClient {

    @GetMapping(value = "/v1/nid/me")
    OAuthResDTO.NaverUser getNaverUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    );
}
