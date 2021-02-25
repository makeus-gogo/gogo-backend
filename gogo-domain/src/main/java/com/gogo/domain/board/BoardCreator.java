package com.gogo.domain.board;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCreator {

    public static Board create(String title) {
        return Board.builder()
            .title(title)
            .memberId(1L)
            .description("description")
            .type(BoardType.MULTI_CHOICE)
            .build();
    }

}
