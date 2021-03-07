package com.gogo.service.answer.dto.response;

import com.gogo.domain.answer.Answer;
import com.gogo.domain.board.Board;
import com.gogo.service.board.dto.response.BoardContentResponse;
import com.gogo.service.board.dto.response.BoardDetailInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class AnswerResultResponse {
    private final Long boardId;
    private final String title;
    private final String description;
    private final List<AnswerResultDto> answerResultDtoList;

}
