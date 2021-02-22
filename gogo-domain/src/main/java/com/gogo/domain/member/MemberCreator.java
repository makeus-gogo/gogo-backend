package com.gogo.domain.member;

public final class MemberCreator {

    public static Member create(String email, MemberProvider memberProvider) {
        return Member.builder()
            .email(email)
            .provider(memberProvider)
            .build();
    }


}
