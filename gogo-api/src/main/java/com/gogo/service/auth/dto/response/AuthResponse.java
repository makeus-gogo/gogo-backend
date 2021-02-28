package com.gogo.service.auth.dto.response;

import com.gogo.domain.member.MemberProvider;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResponse {

    private final AuthType type;

    private final String email;

    private final String name;

    private final MemberProvider provider;

    private final String token;

    public static AuthResponse login(String token) {
        return new AuthResponse(AuthType.LOGIN, null, null, null, token);
    }

    public static AuthResponse signUpWithKaKao(String email, String name) {
        return new AuthResponse(AuthType.SIGN_UP, email, name, MemberProvider.KAKAO, null);
    }

    public static AuthResponse signUpWithGoogle(String email, String name) {
        return new AuthResponse(AuthType.SIGN_UP, email, name, MemberProvider.GOOGLE, null);
    }

    public enum AuthType {
        SIGN_UP,
        LOGIN
    }

}
