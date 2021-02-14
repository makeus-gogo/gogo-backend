package com.gogo.external.google.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class GoogleUserInfoResponse {

    private String id;

    private String email;

    private String name;

    private String picture;

    private String locale;

}
