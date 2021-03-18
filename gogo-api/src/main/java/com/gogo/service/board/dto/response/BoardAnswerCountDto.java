package com.gogo.service.board.dto.response;

import com.gogo.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardAnswerCountDto {
    private final int answerCount;
    private final Board board;
}
