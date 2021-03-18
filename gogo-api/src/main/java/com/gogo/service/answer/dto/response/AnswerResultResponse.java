package com.gogo.service.answer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class AnswerResultResponse {
    private final Long boardId;
    private final String description;
    private final List<AnswerResultDto> answerResultDtoList;

}
