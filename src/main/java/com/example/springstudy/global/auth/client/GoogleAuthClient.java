package com.example.springstudy.global.auth.client;


import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "googleAuthClient",
        url = "https://oauth2.googleapis.com",
        configuration = FeignConfig.class
)
public interface GoogleAuthClient {

    @PostMapping(value = "/token")
    OAuthResDTO.GoogleToken getGoogleToken(
            @RequestParam("grant_type") String grantType,
            @RequestParam("client_id") String clientId,
            @RequestParam("redirect_uri") String redirectURI,
            @RequestParam("code") String code,
            @RequestParam("client_secret") String clientSecret
    );
}
