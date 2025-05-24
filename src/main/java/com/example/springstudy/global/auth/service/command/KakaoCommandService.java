package com.example.springstudy.global.auth.service.command;

import com.example.springstudy.domain.user.converter.UserConverter;
import com.example.springstudy.domain.user.dto.response.UserResDTO;
import com.example.springstudy.domain.user.entity.User;
import com.example.springstudy.domain.user.enums.SocialLogin;
import com.example.springstudy.domain.user.repository.UserRepository;
import com.example.springstudy.global.auth.client.KakaoAuthClient;
import com.example.springstudy.global.auth.client.KakaoUserClient;
import com.example.springstudy.global.auth.dto.response.OAuthResDTO;
import com.example.springstudy.global.auth.enums.Role;
import com.example.springstudy.global.auth.exception.OAuthException;
import com.example.springstudy.global.auth.exception.code.OAuthErrorCode;
import com.example.springstudy.global.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoCommandService implements OAuthCommandService{

    private final UserRepository userRepository;
    private final KakaoAuthClient kakaoAuthClient;
    private final KakaoUserClient kakaoUserClient;
    private final JwtUtil jwtUtil;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String kakaoClientSecret;

    @Override
    public UserResDTO.UserLogin login(
            String code
    ){
        // 인가 코드로 카카오측 토큰 얻어오기
        OAuthResDTO.KakaoToken token = getKakaoToken(code);

        // 유저 정보 불러오기
        OAuthResDTO.KakaoUser kakaoUser = getKakaoUserInfo(token.token_type()+ " " +token.access_token());

        // 회원가입 여부 확인
        User user = findMember(kakaoUser.kakao_account().email());
        if (user == null) {
            user = createMember(kakaoUser);
        }

        // JWT 토큰 발행
        return UserConverter.toUserLongin(
                jwtUtil.createAccessToken(user),
                jwtUtil.createRefreshToken(user)
        );
    }

    // 유저 정보 조회
    private OAuthResDTO.KakaoUser getKakaoUserInfo(String token) {
        try {
             return kakaoUserClient.getKakaoUser(token);
        } catch (Exception e) {
            throw new OAuthException(OAuthErrorCode.OAUTH_USER_INFO_FAIL);
        }
    }

    // 토큰 발급
    private OAuthResDTO.KakaoToken getKakaoToken(String code) {
        try {
            return kakaoAuthClient.getKakaoToken(
                "authorization_code",
                    kakaoApiKey,
                    kakaoRedirectUri,
                    code,
                    kakaoClientSecret
            );
        } catch (Exception e) {
            throw new OAuthException(OAuthErrorCode.OAUTH_TOKEN_FAIL);
        }
    }

    // 회원가입 여부 확인
    private User findMember(String email) {

        return userRepository.findUserByEmail(email).orElse(null);
    }

    // 소셜 회원가입
    private User createMember(OAuthResDTO.KakaoUser kakaoUser) {

        // 유저 정보 생성
        User member = UserConverter.toUser(
                kakaoUser.kakao_account().email(),
                Role.USER,
                SocialLogin.KAKAO
        );
        return userRepository.save(member);
    }
}
