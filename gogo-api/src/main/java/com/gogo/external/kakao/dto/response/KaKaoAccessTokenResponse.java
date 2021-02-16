package com.gogo.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class KaKaoAccessTokenResponse {

    private String tokenType;

    private String accessToken;

    private String expiresIn;

    private String refreshToken;

    private String refreshTokenExpiresIn;

    private String scope;

    @Builder(builderMethodName = "testBuilder")
    public KaKaoAccessTokenResponse(String tokenType, String accessToken, String expiresIn, String refreshToken, String refreshTokenExpiresIn, String scope) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
        this.scope = scope;
    }

}
