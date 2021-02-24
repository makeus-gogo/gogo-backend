package com.gogo.service.comment.dto.response;

import com.gogo.domain.comment.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentInfoResponse {
    private final Long id;
    private final String uuid;
    private final Long memberId;
    private final String description;
    public static CommentInfoResponse of(Comment comment){
        return new CommentInfoResponse(comment.getId(), comment.getUuid(), comment.getMemberId(),comment.getDescription());
    }
}
