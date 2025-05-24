package com.example.springstudy.global.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FrontController {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.scope[0]}")
    private String googleScope1;

    @Value("${spring.security.oauth2.client.registration.google.scope[1]}")
    private String googleScope2;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String naverRedirectUri;

    @GetMapping("/test")
    public String test(Model model, HttpSession session) {
        model.addAttribute("kakaoApiKey", kakaoApiKey);
        model.addAttribute("kakaoRedirectUri", kakaoRedirectUri);
        model.addAttribute("googleClientId", googleClientId);
        model.addAttribute("googleRedirectUri", googleRedirectUri);
        model.addAttribute("googleScope", googleScope1+' '+googleScope2);
        model.addAttribute("naverClientId", naverClientId);
        model.addAttribute("naverRedirectUri", naverRedirectUri);

        String state = UUID.randomUUID().toString();
        String encodedState = URLEncoder.encode(state, StandardCharsets.UTF_8);

        model.addAttribute("state", encodedState);
        session.setAttribute("state", state);
        return "test";
    }
}
