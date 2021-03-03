package com.gogo.controller.auth;

import com.gogo.controller.ApiResponse;
import com.gogo.service.auth.AuthService;
import com.gogo.service.auth.dto.request.AuthRequest;
import com.gogo.service.auth.dto.response.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "카카오톡 인증 요청 API", description = "로그인을 위한 토큰 혹은 회원가입을 위한 정보가 반환됩니다.")
    @GetMapping("/api/v1/auth/kakao")
    public ApiResponse<AuthResponse> handleKaKaoAuthentication(@Valid AuthRequest request) {
        return ApiResponse.of(authService.handleKaKaoAuthentication(request.getCode(), request.getRedirectUri()));
    }

    @Operation(summary = "구글 인증 요청 API", description = "로그인을 위한 토큰 혹은 회원가입을 위한 정보가 반환됩니다.")
    @GetMapping("/api/v1/auth/google")
    public ApiResponse<AuthResponse> handleGoogleAuthentication(@Valid AuthRequest request) {
        return ApiResponse.of(authService.handleGoogleAuthentication(request.getCode(), request.getRedirectUri()));
    }

}
