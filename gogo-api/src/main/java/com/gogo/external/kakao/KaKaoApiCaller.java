package com.gogo.external.kakao;

import com.gogo.external.kakao.dto.response.KaKaoUserInfoResponse;

public interface KaKaoApiCaller {

    KaKaoUserInfoResponse getKaKaoUserProfileInfo(String accessToken);

}
