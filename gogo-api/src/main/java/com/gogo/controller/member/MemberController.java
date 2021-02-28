package com.gogo.controller.member;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.member.MemberService;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import com.gogo.service.member.dto.request.UpdateMemberInfoRequest;
import com.gogo.service.member.dto.response.MemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/signup")
    public ApiResponse<String> signUpMember(@Valid @RequestBody CreateMemberRequest request) {
        return ApiResponse.of(memberService.createMember(request));
    }

    @GetMapping("/api/v1/member")
    public ApiResponse<MemberInfoResponse> getMemberInfo(@LoginUser Long memberId) {
        return ApiResponse.of(memberService.getMemberInfo(memberId));
    }

    @PatchMapping("/api/v1/member")
    public ApiResponse<MemberInfoResponse> updateMemberInfo(@RequestBody UpdateMemberInfoRequest request, @LoginUser Long memberId) {
        return ApiResponse.of(memberService.updateMemberInfo(request, memberId));
    }

}
