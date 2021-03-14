package com.gogo.external.kakao.dto.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
class KaKaoProfileResponse {

    private String nickname;

    private String profileImage;

    public KaKaoProfileResponse(String nickname, String profileImage) {
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

}
