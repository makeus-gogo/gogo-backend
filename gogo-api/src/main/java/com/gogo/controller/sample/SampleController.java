package com.gogo.controller.sample;

import com.gogo.controller.ApiResponse;
import com.gogo.service.sample.SampleService;
import com.gogo.service.sample.dto.SampleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class SampleController {

    private final SampleService sampleService;

    @PostMapping("/api/v1/sample")
    public ApiResponse<String> sample(@Valid @RequestBody SampleRequest request) {
        sampleService.test(request);
        return ApiResponse.OK;
    }

}
