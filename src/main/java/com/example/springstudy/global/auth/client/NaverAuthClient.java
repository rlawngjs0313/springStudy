package com.example.springstudy.global.auth.client;

import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "naverAuthClient",
        url = "https://nid.naver.com/oauth2.0",
        configuration = FeignConfig.class
)
public interface NaverAuthClient {

    @GetMapping(value = "/token")
    OAuthResDTO.NaverToken getNaverToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("client_secret") String clientSecret,
            @RequestParam("code") String code,
            @RequestParam("state") String state
    );
}
