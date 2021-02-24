package com.gogo.controller.comment;

import com.gogo.config.resolver.LoginUser;
import com.gogo.controller.ApiResponse;
import com.gogo.service.comment.CommentService;
import com.gogo.service.comment.dto.request.CreateCommentRequest;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CommentController {

    private final CommentService commentService;

    @PostMapping("api/v1/board/{uuid}/comment")
    public ApiResponse<CommentInfoResponse> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest,
                                                          @PathVariable String uuid,
                                                          @LoginUser Long memberId){
        return ApiResponse.of(commentService.createComment(createCommentRequest,uuid,memberId));
    }

}
