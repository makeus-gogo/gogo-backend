package com.gogo.service.board.dto.response;

import com.gogo.domain.board.BoardContent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BoardContentResponse {

    private final Long id;

    private final String content;

    public static BoardContentResponse of(BoardContent boardContent) {
        return new BoardContentResponse(boardContent.getId(), boardContent.getContent());
    }

}
