package com.gogo.external.google;

import com.gogo.external.google.dto.response.GoogleAccessTokenResponse;
import com.gogo.external.google.dto.response.GoogleUserInfoResponse;

public interface GoogleApiCaller {

    GoogleAccessTokenResponse getGoogleAccessToken(String code, String redirectUri);

    GoogleUserInfoResponse getGoogleUserProfileInfo(String accessToken);

}
