package com.gogo.service.answer.dto.response;

import com.gogo.domain.board.BoardType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class AnswerResultResponse {
    private final Long boardId;
    private final String description;
    private final String pictureUrl;
    private final BoardType type;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;
    private final List<String> hashTags;
    private final List<AnswerResultDto> answerResultDtoList;

}
