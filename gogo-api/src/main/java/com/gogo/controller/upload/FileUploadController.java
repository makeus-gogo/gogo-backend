package com.gogo.controller.upload;

import com.gogo.controller.ApiResponse;
import com.gogo.service.upload.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @Operation(summary = "사진을 업로드 하는 API", description = "해당 URI이 반환됩니다.")
    @PostMapping("/api/v1/upload/image")
    public ApiResponse<String> uploadImage(@RequestPart MultipartFile file) {
        return ApiResponse.of(fileUploadService.uploadImage(file));
    }

}
