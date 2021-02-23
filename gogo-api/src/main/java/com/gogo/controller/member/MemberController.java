package com.gogo.controller.member;

import com.gogo.controller.ApiResponse;
import com.gogo.service.member.MemberService;
import com.gogo.service.member.dto.request.CreateMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/signup")
    public ApiResponse<String> signUpMember(@Valid @RequestBody CreateMemberRequest request) {
        return ApiResponse.of(memberService.createMember(request));
    }

}
