package com.gogo.service.member;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.exception.ConflictException;
import com.gogo.exception.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MemberServiceUtils {

    static Member findMemberById(MemberRepository memberRepository, Long memberId) {
        Member member = memberRepository.findMemberById(memberId);
        if (member == null) {
            throw new NotFoundException(String.format("해당하는 멤버 (%s)가 존재하지 않아요", memberId), "존재하지 않는 유저 입니다.");
        }
        return member;
    }

    static void validateNonExistMember(MemberRepository memberRepository, String email, MemberProvider provider) {
        Member member = memberRepository.findMemberByEmailAndProvider(email, provider);
        if (member != null) {
            throw new ConflictException(String.format("이미 존재하는 멤버 (%s) 입니다", email), "이미 존재하는 유저입니다.");
        }
    }

}
