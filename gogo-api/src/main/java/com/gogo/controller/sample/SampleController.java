package com.gogo.controller.sample;

import com.gogo.config.BaseException;
import com.gogo.config.BaseResponse;
import com.gogo.controller.ApiResponse;
import com.gogo.service.sample.SampleService;
import com.gogo.service.sample.dto.SampleRequest;
import com.gogo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.gogo.config.BaseResponseStatus.SUCCESS;

@RequiredArgsConstructor
@RestController
public class SampleController {

    private final SampleService sampleService;
    private final JwtService jwtService;


    @PostMapping("/api/v1/sample")
    public ApiResponse<String> sample(@Valid @RequestBody SampleRequest request) {
        sampleService.test(request);
        return ApiResponse.OK;
    }

//    @GetMapping("/jwt")
//    public void jwt() {
//        System.out.println(secretKey);
//    }

}
