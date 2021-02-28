package com.gogo.service.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMemberInfoRequest {

    private String name;

    private String profileUrl;

    private UpdateMemberInfoRequest(String name, String profileUrl) {
        this.name = name;
        this.profileUrl = profileUrl;
    }

}
