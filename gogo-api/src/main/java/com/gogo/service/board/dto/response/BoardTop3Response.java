package com.gogo.service.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardTop3Response {
    private final Long boardId;
    private final Long memberId;
    private final String nickname;
    private final String profileImageUrl;
    private final String description;
    private final String boardImageUrl;
}
