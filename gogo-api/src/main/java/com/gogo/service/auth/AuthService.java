package com.gogo.service.auth;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.external.google.GoogleApiCaller;
import com.gogo.external.google.dto.response.GoogleAccessTokenResponse;
import com.gogo.external.google.dto.response.GoogleUserInfoResponse;
import com.gogo.external.kakao.KaKaoApiCaller;
import com.gogo.external.kakao.dto.response.KaKaoAccessTokenResponse;
import com.gogo.external.kakao.dto.response.KaKaoUserInfoResponse;
import com.gogo.service.auth.dto.response.AuthResponse;
import com.gogo.utils.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final GoogleApiCaller googleApiCaller;
    private final KaKaoApiCaller kaKaoApiCaller;

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Transactional(readOnly = true)
    public AuthResponse handleGoogleAuthentication(String code, String redirectUri) {
        GoogleAccessTokenResponse tokenResponse = googleApiCaller.getGoogleAccessToken(code, redirectUri);
        GoogleUserInfoResponse userInfoResponse = googleApiCaller.getGoogleUserProfileInfo(tokenResponse.getAccessToken());

        Member findMember = memberRepository.findMemberByEmailAndProvider(userInfoResponse.getEmail(), MemberProvider.GOOGLE);
        if (findMember == null) {
            return AuthResponse.signUpWithGoogle(userInfoResponse.getEmail(), userInfoResponse.getName());
        }
        return AuthResponse.login(tokenService.encodeSignUpToken(findMember.getId()));
    }

    @Transactional(readOnly = true)
    public AuthResponse handleKaKaoAuthentication(String code, String redirectUri) {
        KaKaoAccessTokenResponse tokenResponse = kaKaoApiCaller.getKaKaoAccessToken(code, redirectUri);
        KaKaoUserInfoResponse userInfoResponse = kaKaoApiCaller.getKaKaoUserProfileInfo(tokenResponse.getAccessToken());

        Member findMember = memberRepository.findMemberByEmailAndProvider(userInfoResponse.getEmail(), MemberProvider.KAKAO);
        if (findMember == null) {
            return AuthResponse.signUpWithKaKao(userInfoResponse.getEmail(), userInfoResponse.getName());
        }
        return AuthResponse.login(tokenService.encodeSignUpToken(findMember.getId()));
    }

}
