package com.gogo.service.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AnswerResponse {
    private Long answerId;
    private Long boardId;
    private final List<AnswerResultDto> answerResultDtoList;
}
