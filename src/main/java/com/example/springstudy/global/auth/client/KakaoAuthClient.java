package com.example.springstudy.global.auth.client;

import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "kakaoAuthClient",
    url = "https://kauth.kakao.com",
    configuration = FeignConfig.class
)
public interface KakaoAuthClient {

    @PostMapping(value = "/oauth/token")
    OAuthResDTO.KakaoToken getKakaoToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectURI,
            @RequestParam("code") String code,
            @RequestParam("client_secret") String clientSecret
    );
}