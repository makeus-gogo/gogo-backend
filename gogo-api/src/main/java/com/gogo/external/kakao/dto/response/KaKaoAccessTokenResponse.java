package com.gogo.external.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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

}
