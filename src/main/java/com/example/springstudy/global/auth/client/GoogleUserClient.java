package com.example.springstudy.global.auth.client;

import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "googleUserClient",
        url = "https://www.googleapis.com",
        configuration = FeignConfig.class
)
public interface GoogleUserClient {

    @GetMapping(value = "/oauth2/v2/userinfo")
    OAuthResDTO.GoogleUser getGoogleUser(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    );
}
