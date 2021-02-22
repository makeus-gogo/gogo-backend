package com.gogo.service.member;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void createMember(CreateMemberRequest request) {
        validateNonExistMember(request.getEmail(), request.getProvider());
        // TODO 토큰 발행 및 반환
        memberRepository.save(request.toEntity());
    }

    private void validateNonExistMember(String email, MemberProvider provider) {
        Member member = memberRepository.findMemberByEmailAndProvider(email, provider);
        if (member != null) {
            throw new IllegalArgumentException(String.format("이미 존재하는 멤버 (%s) 입니다", email));
        }
    }

}
