package com.gogo.service.comment.dto.response;

import com.gogo.domain.comment.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentInfoResponse {
    private final Long commentId;
    private final Long boardId;
    private final Long memberId;
    private final String description;

    public static CommentInfoResponse of(Comment comment){
        return new CommentInfoResponse(comment.getId(), comment.getBoard().getId(),
            comment.getMember().getId(),comment.getDescription());
    }
}
