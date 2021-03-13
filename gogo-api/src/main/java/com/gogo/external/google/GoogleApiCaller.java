package com.gogo.external.google;

import com.gogo.external.google.dto.response.GoogleUserInfoResponse;

public interface GoogleApiCaller {

    GoogleUserInfoResponse getGoogleUserProfileInfo(String accessToken);

}
