package com.gogo.domain.member;

public final class MemberCreator {

    public static Member create(String email) {
        return Member.builder()
            .email(email)
            .build();
    }

}
