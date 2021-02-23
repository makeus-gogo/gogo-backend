package com.gogo.controller.auth;

import com.gogo.controller.ApiResponse;
import com.gogo.service.auth.AuthService;
import com.gogo.service.auth.dto.request.AuthRequest;
import com.gogo.service.auth.dto.response.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @GetMapping("/api/v1/auth/kakao")
    public ApiResponse<AuthResponse> handleKaKaoAuthentication(@Valid AuthRequest request) {
        return ApiResponse.of(authService.handleKaKaoAuthentication(request.getCode(), request.getRedirectUri()));
    }

    @GetMapping("/api/v1/auth/google")
    public ApiResponse<AuthResponse> handleGoogleAuthentication(@Valid AuthRequest request) {
        return ApiResponse.of(authService.handleGoogleAuthentication(request.getCode(), request.getRedirectUri()));
    }

}
