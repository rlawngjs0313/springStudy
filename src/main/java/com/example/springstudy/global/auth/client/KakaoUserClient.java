package com.example.springstudy.global.auth.client;

import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakaoUserClient",
        url = "https://kapi.kakao.com",
        configuration = FeignConfig.class
)
public interface KakaoUserClient {

    @GetMapping(value = "/v2/user/me")
    OAuthResDTO.KakaoUser getKakaoUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    );
}