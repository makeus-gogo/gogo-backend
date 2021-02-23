package com.gogo.service.auth;

import com.gogo.domain.member.MemberCreator;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.external.kakao.KaKaoApiCaller;
import com.gogo.external.kakao.dto.response.KaKaoAccessTokenResponse;
import com.gogo.external.kakao.dto.response.KaKaoUserInfoResponse;
import com.gogo.service.auth.dto.response.AuthResponse;
import com.gogo.utils.StubTokenServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class KaKaoAuthServiceTest {

    private KaKaoAuthService kaKaoAuthService;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void cleanUp() {
        memberRepository.deleteAll();
    }

    @BeforeEach
    void setUpKaKoService() {
        kaKaoAuthService = new KaKaoAuthService(new MockKaKaoAPiCaller(), memberRepository, new StubTokenServiceImpl());
    }

    @Test
    void 카카오_인증시_존재하지_않는_이메일의경우_회원가입을_위한_정보가_반환된다() {
        // when
        AuthResponse response = kaKaoAuthService.handleKaKaoAuthentication("code", "redirectUri");

        // then
        assertThat(response.getType()).isEqualTo(AuthResponse.AuthType.SIGN_UP);
        assertThat(response.getEmail()).isEqualTo("will.seungho@kakao.com");
        assertThat(response.getName()).isEqualTo("강승호");
        assertThat(response.getToken()).isNull();
    }

    @Test
    void 카카오_인증시_이미_존재하는_이메일의경우_로그인이_진행된다() {
        // given
        memberRepository.save(MemberCreator.create("will.seungho@kakao.com", MemberProvider.KAKAO));

        // when
        AuthResponse response = kaKaoAuthService.handleKaKaoAuthentication("code", "redirectUri");

        // then
        assertThat(response.getType()).isEqualTo(AuthResponse.AuthType.LOGIN);
        assertThat(response.getEmail()).isNull();
        assertThat(response.getName()).isNull();
    }

    private static class MockKaKaoAPiCaller implements KaKaoApiCaller {

        @Override
        public KaKaoAccessTokenResponse getKaKaoAccessToken(String code, String redirectUri) {
            return KaKaoAccessTokenResponse.testBuilder()
                .accessToken("accessToken")
                .build();

        }

        @Override
        public KaKaoUserInfoResponse getKaKaoUserProfileInfo(String accessToken) {
            return KaKaoUserInfoResponse.testInstance("will.seungho@kakao.com", "강승호");
        }
    }

}
