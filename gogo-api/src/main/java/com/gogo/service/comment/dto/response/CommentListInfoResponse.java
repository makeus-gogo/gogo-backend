package com.gogo.service.comment.dto.response;

import com.gogo.domain.comment.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentListInfoResponse {
    private final Long commentId;

    private final Long boardId;

    private final Long memberId;

    private final String memberProfileUrl;

    private final String memberName;

    private final String description;

    public static CommentListInfoResponse of(Comment comment){
        return new CommentListInfoResponse(comment.getId(), comment.getBoard().getId(),
            comment.getMember().getId(),comment.getMember().getProfileUrl(),
            comment.getMember().getName(),comment.getDescription());
    }
}

