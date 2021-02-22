package com.gogo.service.member.dto.request;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    private String profileUrl;

    private String deviceToken;

    @NotNull
    private MemberProvider provider;

    @Builder(builderMethodName = "testBuilder")
    public CreateMemberRequest(String email, String name, String profileUrl, String deviceToken, MemberProvider provider) {
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
        this.deviceToken = deviceToken;
        this.provider = provider;
    }

    public Member toEntity() {
        return Member.newInstance(email, name, profileUrl, deviceToken, provider);
    }

}
