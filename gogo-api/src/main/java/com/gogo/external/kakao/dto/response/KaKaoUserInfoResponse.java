package com.gogo.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KaKaoUserInfoResponse {

    private String id;

    private KaKaoAccountResponse kakaoAccount;

    public KaKaoUserInfoResponse(String id, KaKaoAccountResponse kakaoAccount) {
        this.id = id;
        this.kakaoAccount = kakaoAccount;
    }

    @ToString
    @Getter
    private static class KaKaoAccountResponse {

        private String email;

        private KaKaoProfileResponse profile;

        public KaKaoAccountResponse(String email, KaKaoProfileResponse profile) {
            this.email = email;
            this.profile = profile;
        }

        @ToString
        @Getter
        public static class KaKaoProfileResponse {

            private String nickname;

            public KaKaoProfileResponse(String nickname) {
                this.nickname = nickname;
            }

        }

    }

    public static KaKaoUserInfoResponse testInstance(String email, String name) {
        return new KaKaoUserInfoResponse("id", new KaKaoAccountResponse(email, new KaKaoAccountResponse.KaKaoProfileResponse(name)));
    }

    public String getEmail() {
        return this.kakaoAccount.getEmail();
    }

    public String getName() {
        return this.kakaoAccount.getProfile().getNickname();
    }

}
