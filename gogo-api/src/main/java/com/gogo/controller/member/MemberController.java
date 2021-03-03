package com.gogo.controller.member;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.member.MemberService;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import com.gogo.service.member.dto.request.UpdateMemberInfoRequest;
import com.gogo.service.member.dto.response.MemberInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "로그인을 위한 토큰이 반환됩니다")
    @PostMapping("/api/v1/signup")
    public ApiResponse<String> signUpMember(@Valid @RequestBody CreateMemberRequest request) {
        return ApiResponse.of(memberService.createMember(request));
    }

    @Operation(summary = "내 정보를 불러오는 API", description = "Bearer 토큰이 필요합니다")
    @GetMapping("/api/v1/member")
    public ApiResponse<MemberInfoResponse> getMemberInfo(@LoginUser Long memberId) {
        return ApiResponse.of(memberService.getMemberInfo(memberId));
    }

    @Operation(summary = "내 정보를 수정하는 API", description = "Bearer 토큰이 필요합니다")
    @PatchMapping("/api/v1/member")
    public ApiResponse<MemberInfoResponse> updateMemberInfo(@RequestBody UpdateMemberInfoRequest request, @LoginUser Long memberId) {
        return ApiResponse.of(memberService.updateMemberInfo(request, memberId));
    }

}
