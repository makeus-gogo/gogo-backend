package com.gogo.service.member.dto.request;

import com.gogo.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    private String email;

    private String name;

    private String profileUrl;

    private String deviceToken;

    private int birthYear;

    @Builder(builderMethodName = "testBuilder")
    public CreateMemberRequest(String email, String name, String profileUrl, String deviceToken, int birthYear) {
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
        this.deviceToken = deviceToken;
        this.birthYear = birthYear;
    }

    public Member toEntity() {
        return Member.newInstance(email, name, profileUrl, deviceToken, birthYear);
    }

}
