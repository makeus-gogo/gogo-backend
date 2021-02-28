package com.gogo.controller.answer;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.answer.AnswerService;
import com.gogo.service.answer.dto.request.CreateAnswerRequest;
import com.gogo.service.answer.dto.request.PatchAnswerRequest;
import com.gogo.service.answer.dto.response.AnswerInfoResponse;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("api/v1/board/{boardId}/answer")
    public ApiResponse<AnswerInfoResponse> createAnswer(@Valid @RequestBody CreateAnswerRequest createAnswerRequest,
                                                        @PathVariable Long boardId,
                                                        @LoginUser Long memberId) {
        return ApiResponse.of(answerService.createAnswer(createAnswerRequest,memberId,boardId));
    }
    @PatchMapping("api/v1/board/{boardId}/answer")
    public ApiResponse<AnswerInfoResponse> patchAnswer(@Valid @RequestBody PatchAnswerRequest patchAnswerRequest,
                                                        @PathVariable Long boardId,
                                                        @LoginUser Long memberId) {
        return ApiResponse.of(answerService.patchAnswer(patchAnswerRequest,memberId,boardId));
    }
}
