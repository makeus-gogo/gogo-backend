package com.gogo.domain.member.repository;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;

public interface MemberRepositoryCustom {

    Member findMemberByEmailAndProvider(String email, MemberProvider provider);

	Member findMemberById(Long memberId);

}
