package com.gogo.service.answer.dto.response;

import com.gogo.domain.answer.Answer;
import com.gogo.domain.comment.Comment;
import com.gogo.service.comment.dto.response.CommentInfoResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AnswerInfoResponse {
    private final Long id;
    private final Long contentId;

    public static AnswerInfoResponse of(Answer answer){
        return new AnswerInfoResponse(answer.getId(), answer.getBoardContent().getId());
    }
}
