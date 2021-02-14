package com.gogo.external.kakao;

import com.gogo.external.kakao.dto.response.KaKaoAccessTokenResponse;
import com.gogo.external.kakao.dto.response.KaKaoUserInfoResponse;

public interface KaKaoApiCaller {

    KaKaoAccessTokenResponse getKaKaoAccessToken(String code, String redirectUri);

    KaKaoUserInfoResponse getKaKaoUserProfileInfo(String accessToken);

}
