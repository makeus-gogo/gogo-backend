package com.gogo.controller.answer;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.exception.NotFoundException;
import com.gogo.exception.ValidationException;
import com.gogo.service.answer.AnswerService;
import com.gogo.service.answer.dto.request.CreateAnswerRequest;
import com.gogo.service.answer.dto.request.PatchAnswerRequest;
import com.gogo.service.answer.dto.response.AnswerInfoResponse;
import com.gogo.service.answer.dto.response.AnswerResultResponse;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("api/v1/board/{boardId}/answer")
    @Operation(summary = "고민 답변 API",description = "boardId(고민게시글 인덱스), 토큰이 필요합니다, contentId(선택지 인덱스)")
    @ResponseBody
    public ApiResponse<AnswerInfoResponse> createAnswer(@Valid @RequestBody CreateAnswerRequest createAnswerRequest,
                                                        @PathVariable Long boardId,
                                                        @LoginUser Long memberId) {
        if(createAnswerRequest.getContentId()==null){
            throw new ValidationException("고민에 대한 답변을 선택해주세요.","고민에 대한 답변을 선택해주세요.");
        }
        return ApiResponse.of(answerService.createAnswer(createAnswerRequest,memberId,boardId));
    }


    @PatchMapping("api/v1/board/{boardId}/answer")
    @Operation(summary = "고민 답변 수정 API",description = "boardId(고민게시글 인덱스), 토큰이 필요합니다, contentId(선택지 인덱스)")
    @ResponseBody
    public ApiResponse<AnswerInfoResponse> patchAnswer(@Valid @RequestBody PatchAnswerRequest patchAnswerRequest,
                                                        @PathVariable Long boardId,
                                                        @LoginUser Long memberId) {
        if(patchAnswerRequest.getContentId()==null){
            throw new ValidationException("고민에 대한 답변을 선택해주세요.","고민에 대한 답변을 선택해주세요.");
        }
        return ApiResponse.of(answerService.patchAnswer(patchAnswerRequest,memberId,boardId));
    }


    @GetMapping("api/v1/board/{boardId}/answer")
    @Operation(summary = "고민 답변 결과 조회 API",description = "boardId(고민게시글 인덱스), 토큰이 필요합니다")
    @ResponseBody
    public ApiResponse<AnswerResultResponse> getAnswer(@PathVariable Long boardId,
                                                       @LoginUser Long memberId){
        return new ApiResponse("","",answerService.getAnswer(boardId));
    }
}
