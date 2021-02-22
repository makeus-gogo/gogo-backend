package com.gogo.domain.member.repository;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.gogo.domain.member.QMember.member;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findMemberByEmailAndProvider(String email, MemberProvider provider) {
        return queryFactory.selectFrom(member)
            .where(
                member.email.eq(email),
                member.provider.eq(provider)
            )
            .fetchOne();
    }

}
