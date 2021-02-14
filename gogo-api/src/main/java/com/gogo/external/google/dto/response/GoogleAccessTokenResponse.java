package com.gogo.external.google.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GoogleAccessTokenResponse {

    private String accessToken;

    private String refreshToken;

    private String expiresIn;

    private String idToken;

    @Builder
    public GoogleAccessTokenResponse(String accessToken, String refreshToken, String expiresIn, String idToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.idToken = idToken;
    }

}
