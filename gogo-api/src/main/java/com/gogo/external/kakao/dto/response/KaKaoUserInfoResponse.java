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

    @ToString
    @Getter
    private static class KaKaoAccountResponse {

        private String email;

        private Profile profile;

        @ToString
        @Getter
        public static class Profile {

            private String nickname;

        }

    }

    public String getEmail() {
        return this.kakaoAccount.getEmail();
    }

    public String getName() {
        return this.kakaoAccount.getProfile().getNickname();
    }

}
