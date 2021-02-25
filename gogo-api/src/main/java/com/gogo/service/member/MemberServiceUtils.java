package com.gogo.service.member;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberServiceUtils {

    public static Member findMemberById(MemberRepository memberRepository, Long memberId) {
        Member member = memberRepository.findMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException(String.format("해당하는 멤버 (%s)가 존재하지 않아요", memberId));
        }
        return member;
    }

}
