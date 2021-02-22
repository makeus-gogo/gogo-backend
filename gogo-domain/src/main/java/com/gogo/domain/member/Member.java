package com.gogo.domain.member;

import com.gogo.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String profileUrl;

    private String deviceToken;

    @Enumerated(EnumType.STRING)
    private MemberProvider provider;

    @Builder
    public Member(Long id, String email, String name, String profileUrl, String deviceToken, MemberProvider provider) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileUrl = profileUrl;
        this.deviceToken = deviceToken;
        this.provider = provider;
    }

    public static Member newInstance(String email, String name, String profileUrl, String deviceToken, MemberProvider provider) {
        return Member.builder()
            .email(email)
            .name(name)
            .profileUrl(profileUrl)
            .deviceToken(deviceToken)
            .provider(provider)
            .build();
    }

}
