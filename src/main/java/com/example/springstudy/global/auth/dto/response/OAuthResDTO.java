package com.example.springstudy.global.auth.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

public class OAuthResDTO {

    // 카카오
    @Builder
    public record KakaoToken(
            String token_type,
            String access_token,
            Integer expires_in,
            String refresh_token,
            Integer refresh_token_expires_in,
            String scope
    ){}

    @Builder
    public record KakaoUser(
            Long id,
            Boolean has_signed_up,
            LocalDateTime connected_at,
            Properties properties,
            KakaoAccount kakao_account,
            Profile profile
    ){}

    public record Properties(
            String nickname,
            String profile_image,
            String thumbnail_image
    ){}

    public record KakaoAccount(
            String email,
            Boolean is_email_verified,
            Boolean email_needs_agreement,
            Boolean has_email,
            Boolean profile_nickname_needs_agreement,
            Boolean profile_image_needs_agreement,
            Boolean email_needs_argument,
            Boolean is_email_valid,
            Profile profile
    ){}

    public record Profile(
            String nickname,
            String thumbnail_image_url,
            String profile_image_url,
            Boolean is_default_image,
            Boolean is_default_nickname
    ){}

    // 구글
    @Builder
    public record GoogleToken(
            String access_token,
            Integer expires_in,
            String refresh_token,
            Integer refresh_token_expires_in,
            String scope,
            String token_type
    ){}

    @Builder
    public record GoogleUser(
            Long sub,
            String name,
            String given_name,
            String family_name,
            String preferred_username,
            String email,
            String picture
    ){}

    // 네이버
    @Builder
    public record NaverToken(
            String access_token,
            String refresh_token,
            String token_type,
            Integer expires_in,
            String error,
            String error_description
    ){}

    @Builder
    public record NaverUser(
            String resultcode,
            String message,
            NaverResponse response
    ){}

    public record NaverResponse(
            String id,
            String nickname,
            String name,
            String email,
            String gender,
            String age,
            String birthday,
            String profile_image,
            String birthyear,
            String mobile
    ){}
}
