package com.gogo.controller.sample;

import com.gogo.controller.ApiResponse;
import com.gogo.service.sample.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SampleController {

    private final SampleService sampleService;

    @PostMapping("/api/v1/sample")
    public ApiResponse<String> sample(@RequestBody String sample) {
        sampleService.test(sample);
        return ApiResponse.OK;
    }

}
