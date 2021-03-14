package com.gogo.external.kakao.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
class KaKaoAccountResponse {

    private String email;

    private KaKaoProfileResponse profile;

    public KaKaoAccountResponse(String email, KaKaoProfileResponse profile) {
        this.email = email;
        this.profile = profile;
    }

}
