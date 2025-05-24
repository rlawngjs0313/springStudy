package com.example.springstudy.global.auth.service.command;

import com.example.springstudy.domain.user.converter.UserConverter;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.enums.SocialLogin;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.client.GoogleAuthClient;
import com.example.springstudy.global.auth.client.GoogleUserClient;
import com.example.springstudy.global.auth.client.KakaoAuthClient;
import com.example.springstudy.global.auth.client.KakaoUserClient;
import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.auth.enums.Role;
import com.example.springstudy.global.auth.exception.OAuthException;
import com.example.springstudy.global.auth.exception.code.OAuthErrorCode;
import com.example.springstudy.global.auth.util.JwtUtil;
import io.swagger.v3.oas.annotations.info.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthCommandServiceImpl implements OAuthCommandService{

    private final UserRepository userRepository;
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;
    private final GoogleAuthClient googleAuthClient;
    private final GoogleUserClient googleUserClient;
    private final JwtUtil jwtUtil;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Override
    public UserResDTO.UserLogin login(
            String code,
            String provider
    ){
        String email;
        String accessToken;
        SocialLogin socialLogin;

        // 제공자 판단
        switch (provider) {
            case "kakao":
                accessToken = getKakaoToken(code);
                email = getKakaoUserInfo(accessToken);
                socialLogin = SocialLogin.KAKAO;
                break;
            case "google":
                accessToken = getGoogleToken(code);
                email = getGoogleUserInfo(accessToken);
                socialLogin = SocialLogin.GOOGLE;
                break;
            default:
                throw new OAuthException(OAuthErrorCode.OAUTH_PROVIDER_NOT_FOUND);
        }


        // 회원가입 여부 확인
        User user = findMember(email);
        if (user == null) {
            user = createMember(email, socialLogin);
        }

        // JWT 토큰 발행
        return UserConverter.toUserLongin(
                jwtUtil.createAccessToken(user),
                jwtUtil.createRefreshToken(user)
        );
    }

    // 카카오 토큰 발급
    private String getKakaoToken(String code) {
        try {
            return "Bearer " + kakaoAuthClient.getKakaoToken(
                    "authorization_code",
                    kakaoApiKey,
                    kakaoRedirectUri,
                    code,
                    kakaoClientSecret
            ).access_token();
        } catch (Exception e) {
            throw new OAuthException(OAuthErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 카카오 유저 정보 조회
    private String getKakaoUserInfo(String token) {
        try {
             return kakaoUserClient.getKakaoUser(token).kakao_account().email();
        } catch (Exception e) {
            throw new OAuthException(OAuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    // 구글 토큰 발급
    private String getGoogleToken(String code) {
        try {
            return "Bearer " + googleAuthClient.getGoogleToken(
                    "authorization_code",
                    googleClientId,
                    googleRedirectUri,
                    code,
                    googleClientSecret
            ).access_token();
        } catch (Exception e) {
            throw new OAuthException(OAuthErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 구글 유저 정보 조회
    private String getGoogleUserInfo(String token) {
        try {
            return googleUserClient.getGoogleUser(token).email();
        } catch (Exception e) {
            throw new OAuthException(OAuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    // 회원가입 여부 확인
    private User findMember(String email) {

        return userRepository.findUserByEmail(email).orElse(null);
    }

    // 소셜 회원가입
    private User createMember(String email, SocialLogin socialLogin) {

        // 유저 정보 생성
        User member = UserConverter.toUser(
                email,
                Role.USER,
                socialLogin
        );
        return userRepository.save(member);
    }
}
