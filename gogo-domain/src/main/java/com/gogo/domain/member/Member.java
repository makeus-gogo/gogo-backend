package com.gogo.domain.member;

import com.gogo.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Column(nullable = false)
    private String name;

    private String profileUrl;

    private String deviceToken;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberProvider provider;

    @Builder
    public Member(Long id, String email, String name, String profileUrl, String deviceToken, MemberProvider provider) {
        this.id = id;
        this.email = Email.of(email);
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

    public void updateMemberInfo(String name, String profileUrl) {
        if (StringUtils.hasText(name)) {
            this.name = name;
        }
        if (StringUtils.hasText(profileUrl)) {
            this.profileUrl = profileUrl;
        }
    }

    public String getEmail() {
        return this.email.getEmail();
    }

}
