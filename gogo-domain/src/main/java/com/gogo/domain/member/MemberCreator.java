package com.gogo.domain.member;

public final class MemberCreator {

    public static Member create(String email, MemberProvider memberProvider) {
        return Member.builder()
            .email(email)
            .provider(memberProvider)
            .build();
    }

    public static Member create(String email, String name, String profileUrl) {
        return Member.builder()
            .email(email)
            .name(name)
            .profileUrl(profileUrl)
            .provider(MemberProvider.GOOGLE)
            .build();
    }

}
