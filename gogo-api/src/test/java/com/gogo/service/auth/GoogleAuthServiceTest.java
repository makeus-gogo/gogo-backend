package com.gogo.service.auth;

import com.gogo.domain.member.MemberCreator;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.external.google.GoogleApiCaller;
import com.gogo.external.google.dto.response.GoogleAccessTokenResponse;
import com.gogo.external.google.dto.response.GoogleUserInfoResponse;
import com.gogo.service.auth.dto.response.AuthResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GoogleAuthServiceTest {

    private GoogleAuthService googleAuthService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void cleanUp() {
        memberRepository.deleteAll();
    }

    @BeforeEach
    void setUpKaKoService() {
        googleAuthService = new GoogleAuthService(new MockGoogleApiCaller(), memberRepository);
    }

    @Test
    void 구_인증시_존재하지_않는_이메일의경우_회원가입을_위한_정보가_반환된다() {
        // when
        AuthResponse response = googleAuthService.handleGoogleAuthentication("code", "redirectUri");

        // then
        assertThat(response.getType()).isEqualTo(AuthResponse.AuthType.SIGN_UP);
        assertThat(response.getEmail()).isEqualTo("will.seungho@gmail.com");
        assertThat(response.getName()).isEqualTo("강승호");
        assertThat(response.getToken()).isNull();
    }

    @Test
    void 구글_인증시_이미_존재하는_이메일의경우_로그인이_진행된다() {
        // given
        memberRepository.save(MemberCreator.create("will.seungho@gmail.com", MemberProvider.GOOGLE));

        // when
        AuthResponse response = googleAuthService.handleGoogleAuthentication("code", "redirectUri");

        // then
        assertThat(response.getType()).isEqualTo(AuthResponse.AuthType.LOGIN);
        assertThat(response.getEmail()).isNull();
        assertThat(response.getName()).isNull();
    }

    private static class MockGoogleApiCaller implements GoogleApiCaller {

        @Override
        public GoogleAccessTokenResponse getGoogleAccessToken(String code, String redirectUri) {
            return GoogleAccessTokenResponse.testBuilder()
                .accessToken("accessToken")
                .build();
        }

        @Override
        public GoogleUserInfoResponse getGoogleUserProfileInfo(String accessToken) {
            return GoogleUserInfoResponse.builder()
                .email("will.seungho@gmail.com")
                .name("강승호")
                .build();
        }

    }
}
