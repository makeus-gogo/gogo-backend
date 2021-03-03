package com.gogo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    @Operation(summary = "Health Check")
    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.of("pong");
    }

}
