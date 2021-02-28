package com.gogo.domain.hashtag;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashTagCreator {

    public static HashTag create(String tag, Long boardId) {
        return HashTag.of(boardId, 1L, tag);
    }

}
