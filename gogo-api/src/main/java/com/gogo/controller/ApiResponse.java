package com.gogo.controller;

import com.gogo.service.answer.dto.response.AnswerResultResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResponse<T> {

    public static final ApiResponse<String> OK = new ApiResponse<>("", "", "OK");

    private final String httpCode;
    private final String message;
    private T data;

    public ApiResponse(String httpCode, String message, T data) {
        this.httpCode = httpCode;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>("", "", data);
    }


}
