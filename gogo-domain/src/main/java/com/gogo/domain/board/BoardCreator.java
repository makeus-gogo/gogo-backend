package com.gogo.domain.board;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCreator {

    public static Board create(String description, Long memberId) {
        return Board.builder()
            .memberId(memberId)
            .description(description)
            .type(BoardType.MULTI_CHOICE)
            .build();
    }

}
