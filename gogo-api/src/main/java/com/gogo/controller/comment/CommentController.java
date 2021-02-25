package com.gogo.controller.comment;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.comment.CommentService;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.request.UpdateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    @PostMapping("api/v1/board/{boardId}/comment")
    public ApiResponse<CommentInfoResponse> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest,
                                                          @PathVariable Long boardId,
                                                          @LoginUser Long memberId) {
        return ApiResponse.of(commentService.createComment(createCommentRequest, boardId, memberId));
    }


    @GetMapping("api/v1/board/{boardId}/comment")
    public ApiResponse<List<CommentInfoResponse>> getCommentList(@PathVariable Long boardId
    ) {
        return ApiResponse.of(commentService.getCommentList(boardId));
    }

    @PatchMapping("api/v1/comment/{commentId}")
    public ApiResponse<CommentInfoResponse> updateComment(@Valid @RequestBody UpdateCommentRequest updateCommentRequest,
                                                          @LoginUser Long memberId,
                                                          @PathVariable Long commentId) {
        return ApiResponse.of(commentService.updateComment(updateCommentRequest, commentId, memberId));
    }

    @PatchMapping("api/v1/comment/{commentId}/status")
    public ApiResponse<String> deleteComment(@PathVariable Long commentId, @LoginUser Long memberId) {
        commentService.deleteComment(commentId, memberId);
        return ApiResponse.OK;
    }


}
