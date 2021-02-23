package com.gogo.service.auth;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
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
public class KaKaoAuthService {

    private final KaKaoApiCaller kaKaoApiCaller;
    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Transactional(readOnly = true)
    public AuthResponse handleKaKaoAuthentication(String code, String redirectUri) {
        KaKaoAccessTokenResponse tokenResponse = kaKaoApiCaller.getKaKaoAccessToken(code, redirectUri);
        KaKaoUserInfoResponse userInfoResponse = kaKaoApiCaller.getKaKaoUserProfileInfo(tokenResponse.getAccessToken());

        Member findMember = memberRepository.findMemberByEmailAndProvider(userInfoResponse.getEmail(), MemberProvider.KAKAO);
        if (findMember == null) {
            return AuthResponse.signUp(userInfoResponse.getEmail(), userInfoResponse.getName());
        }
        return AuthResponse.login(tokenService.encodeSignUpToken(findMember.getId()));
    }

}
