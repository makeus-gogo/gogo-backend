package com.gogo.service.member;

import com.gogo.domain.member.Member;
import com.gogo.domain.member.MemberProvider;
import com.gogo.domain.member.MemberRepository;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @AfterEach
    void cleanUp() {
        memberRepository.deleteAll();
    }

    @Test
    void 새로운_유저가_회원가입한다() {
        // given
        String email = "will.seungho@gmail.com";
        String name = "강승호";

        CreateMemberRequest request = CreateMemberRequest.testBuilder()
            .email(email)
            .name(name)
            .provider(MemberProvider.KAKAO)
            .build();

        // when
        memberService.createMember(request);

        // then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(1);
        assertThat(memberList.get(0).getEmail()).isEqualTo(email);
        assertThat(memberList.get(0).getName()).isEqualTo(name);
        assertThat(memberList.get(0).getProvider()).isEqualTo(MemberProvider.KAKAO);
    }

    @Test
    void 구글와_카카오는_독립된_이메일을_사용한다() {
        // given
        String email = "will.seungho@gmail.com";
        Member member = Member.builder()
            .email(email)
            .name("강승호")
            .provider(MemberProvider.KAKAO)
            .build();
        memberRepository.save(member);

        CreateMemberRequest request = CreateMemberRequest.testBuilder()
            .email(email)
            .name("호승강")
            .provider(MemberProvider.GOOGLE)
            .build();

        // when
        memberService.createMember(request);

        // then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(2);
    }

    @Test
    void 구글와_카카오는_독립된_이메일을_사용한다_2() {
        // given
        String email = "will.seungho@gmail.com";
        Member member = Member.builder()
            .email(email)
            .name("강승호")
            .provider(MemberProvider.GOOGLE)
            .build();
        memberRepository.save(member);

        CreateMemberRequest request = CreateMemberRequest.testBuilder()
            .email(email)
            .name("호승강")
            .provider(MemberProvider.KAKAO)
            .build();

        // when
        memberService.createMember(request);

        // then
        List<Member> memberList = memberRepository.findAll();
        assertThat(memberList).hasSize(2);
    }

    @Test
    void 회원가입시_이메일은_중복될_수없다() {
        // given
        String email = "will.seungho@gmail.com";
        Member member = Member.builder()
            .email(email)
            .name("강승호")
            .provider(MemberProvider.GOOGLE)
            .build();
        memberRepository.save(member);

        CreateMemberRequest request = CreateMemberRequest.testBuilder()
            .email(email)
            .name("호승강")
            .provider(MemberProvider.GOOGLE)
            .build();

        // when & then
        assertThatThrownBy(() -> {
            memberService.createMember(request);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
