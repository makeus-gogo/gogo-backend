package com.gogo.service.member;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberRepository;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import com.gogo.service.member.dto.request.UpdateMemberInfoRequest;
import com.gogo.service.member.dto.response.MemberInfoResponse;
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
        MemberServiceUtils.validateNonExistMember(memberRepository, request.getEmail(), request.getProvider());
        Member member = memberRepository.save(request.toEntity());
        return tokenService.encodeSignUpToken(member.getId());
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
        return MemberInfoResponse.of(member);
    }

    @Transactional
    public MemberInfoResponse updateMemberInfo(UpdateMemberInfoRequest request, Long memberId) {
        Member member = MemberServiceUtils.findMemberById(memberRepository, memberId);
        member.updateMemberInfo(request.getName(), request.getProfileUrl());
        return MemberInfoResponse.of(member);
    }

}
