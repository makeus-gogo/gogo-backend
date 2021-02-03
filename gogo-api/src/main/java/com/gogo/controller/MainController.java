package com.gogo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MainController {

    @GetMapping("/ping")
    public ApiResponse<String> ping() {
        return ApiResponse.of("pong");
    }

}
