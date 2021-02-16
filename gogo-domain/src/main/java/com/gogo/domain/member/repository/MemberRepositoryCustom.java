package com.gogo.domain.member.repository;

import com.gogo.domain.member.Member;

public interface MemberRepositoryCustom {

    Member findMemberByEmail(String email);

}
