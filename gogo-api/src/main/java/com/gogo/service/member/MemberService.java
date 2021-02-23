package com.gogo.service.member;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import com.gogo.utils.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final TokenService tokenService;

    @Transactional
    public String createMember(CreateMemberRequest request) {
        validateNonExistMember(request.getEmail(), request.getProvider());
        Member member = memberRepository.save(request.toEntity());
        return tokenService.encodeSignUpToken(member.getId());
    }

    private void validateNonExistMember(String email, MemberProvider provider) {
        Member member = memberRepository.findMemberByEmailAndProvider(email, provider);
        if (member != null) {
            throw new IllegalArgumentException(String.format("이미 존재하는 멤버 (%s) 입니다", email));
        }
    }

}
