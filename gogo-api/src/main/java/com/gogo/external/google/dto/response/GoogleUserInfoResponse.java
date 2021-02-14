package com.gogo.external.google.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class GoogleUserInfoResponse {

    private String id;

    private String email;

    private String name;

    private String picture;

    private String locale;

    @Builder
    public GoogleUserInfoResponse(String id, String email, String name, String picture, String locale) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.locale = locale;
    }

}
